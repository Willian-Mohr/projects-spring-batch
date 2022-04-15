package br.com.wohr.processorclassifierjob.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.wohr.processorclassifierjob.dominio.Transacao;

public class TransacaoProcessor implements ItemProcessor<Transacao, Transacao> {

	@Override
	public Transacao process(Transacao transacao) throws Exception {

		System.out.println(String.format("Aplicando regras de negócio na transação %s", transacao.getId()));
		
		return transacao;
	}

}
