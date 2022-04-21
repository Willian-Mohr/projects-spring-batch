package br.com.wohr.projectcreditcardbill.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transacao {

	private int id;
	private CartaoCredito cartaoCredito;
	private String descricao;
	private Double valor;
	private Date data;

}
