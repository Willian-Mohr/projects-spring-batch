package br.com.wohr.projectjdbccursor.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {

	private String nome;
	private String sobrenome;
	private String idade;
	private String email;

}