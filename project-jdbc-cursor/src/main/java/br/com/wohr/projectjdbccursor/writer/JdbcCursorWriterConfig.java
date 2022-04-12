package br.com.wohr.projectjdbccursor.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wohr.projectjdbccursor.dominio.Cliente;

@Configuration
public class JdbcCursorWriterConfig {
	
	@Bean
	public ItemWriter<Cliente> jdbcCursorWriter() {
		
		return clientes -> clientes.forEach(System.out::println);
		
	}
}
