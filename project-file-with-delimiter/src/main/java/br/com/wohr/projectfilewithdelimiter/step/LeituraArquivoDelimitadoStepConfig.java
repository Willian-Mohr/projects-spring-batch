package br.com.wohr.projectfilewithdelimiter.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wohr.projectfilewithdelimiter.domain.Cliente;

@Configuration
public class LeituraArquivoDelimitadoStepConfig {
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step leituraArquivoDelimitadoStep(ItemReader<Cliente> leituraArquivoDelimitadoReader,
			ItemWriter<Cliente> arquivoDelimitadoWriter) {
		
		return stepBuilderFactory
				.get("leituraArquivoDelimitadoStep")
				.<Cliente, Cliente>chunk(1)
				.reader(leituraArquivoDelimitadoReader)
				.writer(arquivoDelimitadoWriter)
				.build();
		
	}
	
}
