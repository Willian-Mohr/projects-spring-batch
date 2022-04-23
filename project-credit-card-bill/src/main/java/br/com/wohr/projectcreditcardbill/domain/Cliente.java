package br.com.wohr.projectcreditcardbill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Cliente {
	
	@NonNull
	private Integer id;
	private String nome, endereco;

}
