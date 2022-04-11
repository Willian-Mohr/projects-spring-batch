package br.com.wohr.projectfilewithmultipleformats.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cliente {
	
	private String nome;
	private String sobrenome;
	private String idade;
	private String email;
	
	private List<Transacao> transacoes = new ArrayList<>();
	
}
