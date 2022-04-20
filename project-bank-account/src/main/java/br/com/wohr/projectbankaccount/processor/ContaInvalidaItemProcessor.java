package br.com.wohr.projectbankaccount.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.wohr.projectbankaccount.dominio.Cliente;
import br.com.wohr.projectbankaccount.dominio.Conta;

public class ContaInvalidaItemProcessor implements ItemProcessor<Cliente, Conta> {

	@Override
	public Conta process(Cliente cliente) throws Exception {
		
		Conta conta = new Conta();
		conta.setClienteId(cliente.getEmail());
		return conta;
	}

}
