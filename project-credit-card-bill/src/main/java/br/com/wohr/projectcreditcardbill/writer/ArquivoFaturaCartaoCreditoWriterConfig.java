package br.com.wohr.projectcreditcardbill.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import br.com.wohr.projectcreditcardbill.domain.Cliente;
import br.com.wohr.projectcreditcardbill.domain.FaturaCartaoCredito;
import br.com.wohr.projectcreditcardbill.domain.Transacao;

@Configuration
public class ArquivoFaturaCartaoCreditoWriterConfig extends MultiResourceItemWriter<Cliente> {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Cliente cliente;
	
	@Bean
	public MultiResourceItemWriter<FaturaCartaoCredito> arquivosFaturaCartaoCredito(){
		
		return new MultiResourceItemWriterBuilder<FaturaCartaoCredito>()
				.name("arquivosFaturaCartaoCredito")
				.resource(new FileSystemResource("files/fatura-"))
				.itemCountLimitPerResource(1)
				.resourceSuffixCreator(customMultiResourceItemWriter())
				.delegate(arquivoFaturaCartaoCredito())
				.build();
		
	}

	private ResourceSuffixCreator customMultiResourceItemWriter() {
		
		return new ResourceSuffixCreator() {
			
			@Override
			public String getSuffix(int index) {
				return cliente == null? ".txt" : cliente.getNome() + ".txt";
			}
		};
	}
	
	@BeforeWrite
	public void beforeWrite(List<FaturaCartaoCredito> pessoas) {
		cliente = pessoas.get(0).getCliente();
	}

	private FlatFileItemWriter<FaturaCartaoCredito> arquivoFaturaCartaoCredito() {
		
		return new FlatFileItemWriterBuilder<FaturaCartaoCredito>()
				.name("arquivoFaturaCartaoCredito")
				.resource(new FileSystemResource("files/fatura.txt"))
				.lineAggregator(lineAggregator())
				.headerCallback(headerCallback())
				.footerCallback(footerCallback())
				.build();
	}

	private LineAggregator<FaturaCartaoCredito> lineAggregator() {
		
		return new LineAggregator<FaturaCartaoCredito>() {

			@Override
			public String aggregate(FaturaCartaoCredito faturaCartaoCredito) {
				
				StringBuilder writer = new StringBuilder();
				
				writer.append(String.format("Nome: %s \n", faturaCartaoCredito.getCliente().getNome()));
				writer.append(String.format("Endereço: %s \n", faturaCartaoCredito.getCliente().getEndereco()));
				writer.append(String.format("Fatura completa do cartão: %d \n", faturaCartaoCredito.getCartaoCredito().getNumeroCartaoCredito()));
				writer.append("---------------------------------------------------------------------------------------------------------------- \n");
				writer.append("DATA DESCRIÇÃO VALOR \n");
				writer.append("---------------------------------------------------------------------------------------------------------------- \n");
				
				for (Transacao transacao : faturaCartaoCredito.getTransacoes()) {
					writer.append(String.format("\n[%10s] %-80s - %s", dateFormat.format(transacao.getData()),
																	   transacao.getDescricao(),
																	   String.valueOf(transacao.getValor())));
				}
				
				return writer.toString();
			}
		};
	}
	
	private FlatFileHeaderCallback headerCallback() {
		
		return new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				
				writer.append(String.format("%121s \n", "Cartão XPTO"));
				writer.append(String.format("%121s \n\n", "Rua xxx de xxx, 4370"));
			}
		};
	}
	
	@Bean
	public FlatFileFooterCallback footerCallback() {
		return new TotalTransacoesFooterCallback();
	}
}
