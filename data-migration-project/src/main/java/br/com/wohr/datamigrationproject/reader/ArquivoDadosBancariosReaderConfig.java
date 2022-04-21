package br.com.wohr.datamigrationproject.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import br.com.wohr.datamigrationproject.domain.DadosBancarios;

@Configuration
public class ArquivoDadosBancariosReaderConfig {
	
	@Bean
	public FlatFileItemReader<DadosBancarios> dadosBancariosReader() {
		return new FlatFileItemReaderBuilder<DadosBancarios>()
				.name("dadosBancariosReader")
				.resource(new FileSystemResource("files/dados_bancarios.csv"))
				.delimited()
				.names("pessoaId", "agencia", "conta", "banco", "id")
				.addComment("--")
				.fieldSetMapper(fieldSetMapper())
				.build();
	}

	private FieldSetMapper<DadosBancarios> fieldSetMapper() {
		
		return new FieldSetMapper<DadosBancarios>() {

			@Override
			public DadosBancarios mapFieldSet(FieldSet fieldSet) throws BindException {
				
				DadosBancarios dadosBancarios = new DadosBancarios(
						fieldSet.readInt("pessoaId"),
						fieldSet.readInt("agencia"),
						fieldSet.readInt("conta"),
						fieldSet.readInt("banco"),
						fieldSet.readInt("id"));
				
				return dadosBancarios;
			}
		};
	}
}
