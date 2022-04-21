package br.com.wohr.datamigrationproject.reader;

import java.util.Date;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import br.com.wohr.datamigrationproject.domain.Pessoa;

@Configuration
public class ArquivoPessoaReaderConfig {
	
	@Bean
	public FlatFileItemReader<Pessoa> arquivoPessoaReader() {
		
		return new FlatFileItemReaderBuilder<Pessoa>()
				.name("arquivoPessoaReader")
				.resource(new FileSystemResource("files/pessoas.csv"))
				.delimited()
				.names("id", "idade", "nome", "email", "dataNascimento")
				.addComment("--")
				.fieldSetMapper(fieldSetMapper())
				.build();
	}

	private FieldSetMapper<Pessoa> fieldSetMapper() {
		
		return new FieldSetMapper<Pessoa>() {

			@Override
			public Pessoa mapFieldSet(FieldSet fieldSet) throws BindException {
				Pessoa pessoa = new Pessoa(
						fieldSet.readInt("id"),
						fieldSet.readInt("idade"),
						fieldSet.readString("nome"),
						fieldSet.readString("email"),
						new Date(fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime()));
				
				return pessoa;
			}
		};
	}
}