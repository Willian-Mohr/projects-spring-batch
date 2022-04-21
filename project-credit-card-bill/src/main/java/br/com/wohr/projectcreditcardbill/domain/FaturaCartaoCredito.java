package br.com.wohr.projectcreditcardbill.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaturaCartaoCredito {

	private Cliente cliente;
	private CartaoCredito cartaoCredito;
	private List<Transacao> transacoes = new ArrayList<>();

}
