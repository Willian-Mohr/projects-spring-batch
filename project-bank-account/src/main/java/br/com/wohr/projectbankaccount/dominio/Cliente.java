package br.com.wohr.projectbankaccount.dominio;

import lombok.Data;

@Data
public class Cliente {
	
	private String email;
	private String nome;
	private Integer idade;
	private Double faixaSalarial;

}
