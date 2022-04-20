package br.com.wohr.projectbankaccount.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.wohr.projectbankaccount.dominio.Cliente;
import br.com.wohr.projectbankaccount.dominio.Conta;
import br.com.wohr.projectbankaccount.dominio.TipoConta;

public class ContaOuroItemProcessor implements ItemProcessor<Cliente, Conta> {
	
	@Override
	public Conta process(Cliente cliente) throws Exception {
		
		Conta conta = new Conta();
		
		conta.setClienteId(cliente.getEmail());
		conta.setTipo(TipoConta.OURO);
		conta.setLimite(1000.0);
		
		return conta;
	}
	
}
