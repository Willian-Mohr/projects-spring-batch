package br.com.wohr.processorscriptjob.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wohr.processorscriptjob.dominio.Cliente;

@Configuration
public class ProcessadorScriptWriterConfig {
	
	@Bean
	public ItemWriter<Cliente> processadorScriptWriter() {
		return clientes -> clientes.forEach(System.out::println);
	}
}
