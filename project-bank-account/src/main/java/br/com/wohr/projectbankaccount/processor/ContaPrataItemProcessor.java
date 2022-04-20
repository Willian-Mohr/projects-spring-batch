package br.com.wohr.projectbankaccount.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.wohr.projectbankaccount.dominio.Cliente;
import br.com.wohr.projectbankaccount.dominio.Conta;
import br.com.wohr.projectbankaccount.dominio.TipoConta;

public class ContaPrataItemProcessor implements ItemProcessor<Cliente, Conta> {

	@Override
	public Conta process(Cliente cliente) throws Exception {
		
		Conta conta = new Conta();
		
		conta.setClienteId(cliente.getEmail());
		conta.setTipo(TipoConta.PRATA);
		conta.setLimite(500.0);
		
		return conta;
	}

}
