package br.com.wohr.projectbudgetstatement.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import br.com.wohr.projectbudgetstatement.dominio.GrupoLancamento;

@Component
public class DemonstrativoOrcamentarioRodape implements FlatFileFooterCallback {

	private Double totalGeral = 0.0;
	
	@BeforeWrite
	public void beforeWrite(List<GrupoLancamento> grupos) {
		
		grupos.stream().forEach(x -> totalGeral += x.getTotal());
		
	}

	@Override
	public void writeFooter(Writer writer) throws IOException {

		writer.append("\n");
		writer.append(String.format("\t\t\t\t\t\t\t  Total: %s \n", NumberFormat.getCurrencyInstance().format(totalGeral)));
		writer.append(String.format("\t\t\t\t\t\t\t  Código de Autenticação: %s \n", "fkyew6868fewjfhjjewf"));
		
	}
	
	

}
