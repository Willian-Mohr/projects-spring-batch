package br.com.wohr.projectcreditcardbill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartaoCredito {

	private int numeroCartaoCredito;
	private Cliente cliente;

}
