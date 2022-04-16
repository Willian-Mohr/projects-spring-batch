package br.com.wohr.projectfilewithdelimiter.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.wohr.projectfilewithdelimiter.domain.Cliente;

@Configuration
public class ArquivoDelimitadoWriterConfig {
	
	@StepScope
	@Bean
	public FlatFileItemWriter<Cliente> arquivoDelimitadoWriter(
			@Value("#{jobParameters['arquivoClienteSaida']}") Resource arquivoClienteSaida) {
		
//		return items -> items.forEach(System.out::println);
		
		return new FlatFileItemWriterBuilder<Cliente>()
				.name("escritaArquivoLarguraFixaWriter")
				.resource(arquivoClienteSaida)
				.delimited()
				.delimiter(";")
				.names("nome", "sobrenome", "idade", "email")
				.build();

	}
	
}
