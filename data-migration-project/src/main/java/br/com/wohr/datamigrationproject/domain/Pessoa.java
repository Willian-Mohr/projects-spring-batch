package br.com.wohr.datamigrationproject.domain;

import java.util.Date;

import org.apache.logging.log4j.util.Strings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pessoa {

	private int id, idade;
	private String nome, email;
	private Date dataNascimento;

	public boolean isValida() {
		return !Strings.isBlank(nome) && !Strings.isBlank(email) && dataNascimento != null;
	}
}
