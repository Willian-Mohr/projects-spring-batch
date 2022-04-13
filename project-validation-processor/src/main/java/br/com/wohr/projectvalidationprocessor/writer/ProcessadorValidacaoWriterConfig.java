package br.com.wohr.projectvalidationprocessor.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wohr.projectvalidationprocessor.dominio.Cliente;

@Configuration
public class ProcessadorValidacaoWriterConfig {
	
	@Bean
	public ItemWriter<Cliente> processadorValidacaoWriter() {
		return clientes -> clientes.forEach(System.out::println);
	}
}
