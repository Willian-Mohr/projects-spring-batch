package br.com.wohr.projectsendingpromotionemail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {

	private Integer id;
	private String nome, email;

}
