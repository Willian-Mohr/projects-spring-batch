package br.com.wohr.projectvalidationprocessor.dominio;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class Cliente {

	@NotNull
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[a-zA-Z\\s]+", message = "Nome deve ser alfábetico")
	private String nome;

	@NotNull
	@Range(min = 18, max = 200)
	private Integer idade;

	@NotNull
	@Size(min = 1, max = 50)
//	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z]{2,6}$", message = "Email inválido")
	@Email(message = "Email inválido")
	private String email;

}
