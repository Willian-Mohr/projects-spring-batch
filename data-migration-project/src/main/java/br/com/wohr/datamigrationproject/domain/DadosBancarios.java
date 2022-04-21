package br.com.wohr.datamigrationproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DadosBancarios {

	private int pessoaId, agencia, conta, banco, id;

}
