package br.com.wohr.projectfilewithmultipleformats.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.wohr.projectfilewithmultipleformats.domain.Cliente;

@Configuration
public class ArquivoMultiplosFormatosReaderConfig {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@StepScope
	@Bean
	public FlatFileItemReader arquivoMultiplosFormatosItemReader(
			@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes,
			LineMapper lineMapper) {

		return new FlatFileItemReaderBuilder<Cliente>()
				.name("arquivoMultiplosFormatosItemReader")
				.resource(arquivoClientes)
				.lineMapper(lineMapper)
				.targetType(Cliente.class)
				.build();
		
	}

}
