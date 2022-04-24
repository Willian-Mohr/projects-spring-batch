package br.com.wohr.projectsendingpromotionemail.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import br.com.wohr.projectsendingpromotionemail.domain.InteresseProdutoCliente;

@Component
public class ProcessarEmailProdutoClienteProcessor implements ItemProcessor<InteresseProdutoCliente, SimpleMailMessage>{

	@Override
	public SimpleMailMessage process(InteresseProdutoCliente interesseProdutoCliente) throws Exception {
		
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setFrom("xpto@no-reply.com");
		email.setTo(interesseProdutoCliente.getCliente().getEmail());
		email.setSubject("Promoção Imperdível !!!!");
		email.setText(gerarTextoPromocao(interesseProdutoCliente));
		
		Thread.sleep(2000);
		
		return email;

	}

	private String gerarTextoPromocao(InteresseProdutoCliente interesseProdutoCliente) {

		StringBuilder writer = new StringBuilder();
		
		writer.append(String.format("Olá, %s! \n\n", interesseProdutoCliente.getCliente().getNome()));
		writer.append(String.format("Essa promoção pode ser de seu intresse: \n\n"));
		writer.append(String.format("%s - %s \n\n", interesseProdutoCliente.getProduto().getNome(), interesseProdutoCliente.getProduto().getDescricao()));
		writer.append(String.format("Por apenas: R$%s!",
				String.valueOf(interesseProdutoCliente.getProduto().getPreco())));
		
		return writer.toString();
	}

}
