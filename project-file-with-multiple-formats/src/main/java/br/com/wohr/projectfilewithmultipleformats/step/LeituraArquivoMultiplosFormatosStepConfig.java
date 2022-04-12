package br.com.wohr.projectfilewithmultipleformats.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wohr.projectfilewithmultipleformats.domain.Cliente;

@Configuration
public class LeituraArquivoMultiplosFormatosStepConfig {
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step leituraArquivoMultiplosFormatosStep(
			MultiResourceItemReader<Cliente> multiplosArquivosClienteTransacaoReader,
			ItemWriter leituraArquivoMultiplosFormatosItemWriter) {
		
		return stepBuilderFactory
				.get("leituraArquivoMultiplosFormatosStep")
				.chunk(1)
				.reader(multiplosArquivosClienteTransacaoReader)
				.writer(leituraArquivoMultiplosFormatosItemWriter)
				.build();
		
	}
	
}
