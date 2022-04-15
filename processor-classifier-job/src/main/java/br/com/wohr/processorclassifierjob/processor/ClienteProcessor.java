package br.com.wohr.processorclassifierjob.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.wohr.processorclassifierjob.dominio.Cliente;

public class ClienteProcessor implements ItemProcessor<Cliente, Cliente> {

	@Override
	public Cliente process(Cliente cliente) throws Exception {
		
		System.out.println(String.format("Aplicando regras de negócio no cliente %s", cliente.getEmail()));
		
		return cliente;
	}

}
