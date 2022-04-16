package br.com.wohr.projectbudgetstatement.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wohr.projectbudgetstatement.dominio.GrupoLancamento;
import br.com.wohr.projectbudgetstatement.reader.GrupoLancamentoReader;

@Configuration
public class DemonstrativoOrcamentarioStepConfig {
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step demonstrativoOrcamentarioStep(
			GrupoLancamentoReader demonstrativoOrcamentarioReader,
			ItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter) {
		
		return stepBuilderFactory
				.get("demonstrativoOrcamentarioStep")
				.<GrupoLancamento,GrupoLancamento>chunk(100)
				.reader(demonstrativoOrcamentarioReader)
				.writer(demonstrativoOrcamentarioWriter)
				.build();
	}
}
