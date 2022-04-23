package br.com.wohr.projectcreditcardbill.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import br.com.wohr.projectcreditcardbill.domain.FaturaCartaoCredito;
import br.com.wohr.projectcreditcardbill.domain.Transacao;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito> {

	@NonNull
	private ItemStreamReader<Transacao> delegate;

	private Transacao transacaoAtual;

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		delegate.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		delegate.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
	}

	@Override
	public FaturaCartaoCredito read() throws Exception {

		if (transacaoAtual == null) {
			transacaoAtual = delegate.read();
		}

		FaturaCartaoCredito faturaCartaoCredito = null;
		
		Transacao transacao = transacaoAtual;
		transacaoAtual = null;

		if (transacao != null) {

			faturaCartaoCredito = new FaturaCartaoCredito(transacao.getCartaoCredito().getCliente(), transacao.getCartaoCredito(), transacao);

			while (isTransacaoRelacionada(transacao)) {
				faturaCartaoCredito.getTransacoes().add(transacaoAtual);
			}
		}

		return faturaCartaoCredito;
	}

	private boolean isTransacaoRelacionada(Transacao transacao) throws Exception {

		return peek() != null 
			   && transacao.getCartaoCredito()
				    	   .getNumeroCartaoCredito() == transacaoAtual.getCartaoCredito()
							   								          .getNumeroCartaoCredito();
	}

	private Transacao peek() throws Exception {

		transacaoAtual = delegate.read();

		return transacaoAtual;
	}
}
