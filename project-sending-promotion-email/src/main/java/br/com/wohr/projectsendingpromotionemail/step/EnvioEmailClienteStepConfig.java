package br.com.wohr.projectsendingpromotionemail.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import br.com.wohr.projectsendingpromotionemail.domain.InteresseProdutoCliente;

@Configuration
public class EnvioEmailClienteStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step envioEmailClienteStep(
			ItemReader<InteresseProdutoCliente> lerInteresseProdutoClienteReader,
			ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> processaEmailProdutoClienteProcessor,
			ItemWriter<SimpleMailMessage> enviarEmailProdutoClienteWriter) {
		
		return stepBuilderFactory
				.get("envioEmailClienteStep")
				.<InteresseProdutoCliente, SimpleMailMessage>chunk(1)
				.reader(lerInteresseProdutoClienteReader)
				.processor(processaEmailProdutoClienteProcessor)
				.writer(enviarEmailProdutoClienteWriter)
				.build();
	}

}
