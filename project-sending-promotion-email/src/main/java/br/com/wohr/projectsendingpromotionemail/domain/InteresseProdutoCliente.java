package br.com.wohr.projectsendingpromotionemail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InteresseProdutoCliente {

	private Cliente cliente;
	private Produto produto;

}
