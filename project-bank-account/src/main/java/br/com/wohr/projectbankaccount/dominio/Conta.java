package br.com.wohr.projectbankaccount.dominio;

import java.text.NumberFormat;

import lombok.Data;

@Data
public class Conta {
	
	private Integer id;
	private TipoConta tipo;
	private Double limite;
	private String clienteId;
	
	@Override
	public String toString() {
		return "Conta{" +
				"clienteId=" + this.clienteId +
				",tipo=" + this.tipo +
				",limite=" + NumberFormat.getCurrencyInstance().format(this.limite) +
				"}";
	}	
}
