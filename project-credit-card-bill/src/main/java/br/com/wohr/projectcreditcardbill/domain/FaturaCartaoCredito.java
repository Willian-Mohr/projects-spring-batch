package br.com.wohr.projectcreditcardbill.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FaturaCartaoCredito {

	private Cliente cliente;
	
	private CartaoCredito cartaoCredito;
	
	private List<Transacao> transacoes = new ArrayList<>();

	public FaturaCartaoCredito(Cliente cliente, CartaoCredito cartaoCredito, Transacao transacoes) {
		
		this.cliente = cliente;
		this.cartaoCredito = cartaoCredito;
		this.transacoes.add(transacoes);
	}
}
