package br.com.wohr.projectbudgetstatement.dominio;

import java.util.Date;

import lombok.Data;

@Data
public class Lancamento {

	private String descricao;
	private Date data;
	private Double valor;

}
