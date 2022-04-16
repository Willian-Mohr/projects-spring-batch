package br.com.wohr.projectbudgetstatement.dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GrupoLancamento {

	private Integer codigoNaturezaDespesa;
	private String descricaoNaturezaDespesa;

	private List<Lancamento> lancamentos = new ArrayList<>();

	private Lancamento lancamentoTmp;

	public Double getTotal() {
		return lancamentos.stream().mapToDouble(Lancamento::getValor).reduce(0.0, Double::sum);
	}
}
