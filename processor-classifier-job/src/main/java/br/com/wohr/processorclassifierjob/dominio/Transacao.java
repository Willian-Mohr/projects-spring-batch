package br.com.wohr.processorclassifierjob.dominio;

import lombok.Data;

@Data
public class Transacao {
	
	public String id;
	public String descricao;
	public Double valor;

}
