package br.com.wohr.projectsendingpromotionemail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Produto {
	
	private Integer id;
	private String nome, descricao;
	private Double preco;

}
