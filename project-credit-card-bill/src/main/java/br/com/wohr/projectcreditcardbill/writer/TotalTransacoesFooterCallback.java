package br.com.wohr.projectcreditcardbill.writer;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import br.com.wohr.projectcreditcardbill.domain.FaturaCartaoCredito;

public class TotalTransacoesFooterCallback implements FlatFileFooterCallback {

	private Double total = 0.0;

	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.write(String.format("\n %121s", "Total: " + String.valueOf(total)));
	}

	@BeforeWrite
	public void beforeWrite(List<FaturaCartaoCredito> faturas) {

		for (FaturaCartaoCredito faturaCartaoCredito : faturas) {
			total += faturaCartaoCredito.getTotal();
		}
	}

	@AfterChunk
	public void afterChunk(ChunkContext chunkContext) {
		total = 0.0;
	}
}
