package br.com.wohr.projectcreditcardbill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {
	
	private int id;
	private String nome, endereco;

}
