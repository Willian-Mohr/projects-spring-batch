package br.com.wohr.projectbankaccount.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wohr.projectbankaccount.dominio.Cliente;
import br.com.wohr.projectbankaccount.dominio.Conta;

@Configuration
public class CriacaoContasStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step criacaoContasStep(
			ItemReader<Cliente> leituraClientesReader, 
			ItemProcessor<Cliente, Conta> geracaoContaProcessor,
			ItemWriter<Conta> impressaoContaWriter) {
		
		return stepBuilderFactory
				.get("criacaoContasStep")
				.<Cliente, Conta>chunk(10)
				.reader(leituraClientesReader)
				.processor(geracaoContaProcessor)
				.writer(impressaoContaWriter)
				.build();
	}
}
