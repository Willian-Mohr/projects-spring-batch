package br.com.wohr.projectcreditcardbill.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.wohr.projectcreditcardbill.domain.Cliente;
import br.com.wohr.projectcreditcardbill.domain.FaturaCartaoCredito;

public class CarregarDadosClienteProcessor implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "https://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d";

	@Override
	public FaturaCartaoCredito process(FaturaCartaoCredito faturaCartaoCredito) throws Exception {

		String uri = String.format(URL, faturaCartaoCredito.getCliente());

		ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new ValidationException("Cliente não encontrado!");
		}

		faturaCartaoCredito.setCliente(response.getBody());

		return faturaCartaoCredito;
	}
}
