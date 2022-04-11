package br.com.wohr.projectfilewithmultipleformats.reader;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wohr.projectfilewithmultipleformats.domain.Cliente;
import br.com.wohr.projectfilewithmultipleformats.domain.Transacao;

@Configuration
public class ClienteTransacaoLineMapperConfig {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public PatternMatchingCompositeLineMapper lineMapper() {

		PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();

		lineMapper.setTokenizers(tokenizers());
		lineMapper.setFieldSetMappers(fieldSetMappers());

		return lineMapper;

	}

	@SuppressWarnings("rawtypes")
	private Map<String, FieldSetMapper> fieldSetMappers() {

		Map<String, FieldSetMapper> fieldSetMapper = new HashMap<>();

		fieldSetMapper.put("0*", fieldSetMapper(Cliente.class));
		fieldSetMapper.put("1*", fieldSetMapper(Transacao.class));

		return fieldSetMapper;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private FieldSetMapper fieldSetMapper(Class classe) {

		BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();

		fieldSetMapper.setTargetType(classe);

		return fieldSetMapper;
	}

	private Map<String, LineTokenizer> tokenizers() {

		Map<String, LineTokenizer> tokenizers = new HashMap<>();

		tokenizers.put("0*", clienteLineTokenizers());
		tokenizers.put("1*", transacaoLineTokenizers());

		return tokenizers;

	}

	private LineTokenizer transacaoLineTokenizers() {

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setNames("id", "descricao", "valor");
		lineTokenizer.setIncludedFields(1, 2, 3);

		return lineTokenizer;

	}

	private LineTokenizer clienteLineTokenizers() {

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
		lineTokenizer.setIncludedFields(1, 2, 3, 4);

		return lineTokenizer;

	}

}
