package br.com.wohr.projectsendingpromotionemail.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import br.com.wohr.projectsendingpromotionemail.domain.Cliente;
import br.com.wohr.projectsendingpromotionemail.domain.InteresseProdutoCliente;
import br.com.wohr.projectsendingpromotionemail.domain.Produto;

@Configuration
public class LerInteresseProdutoClienteReaderConfig {
	
	@Bean
	public JdbcCursorItemReader<InteresseProdutoCliente> lerInteresseProdutoClienteReader(
			@Qualifier("appDataSource") DataSource dataSource){
		
		return new JdbcCursorItemReaderBuilder<InteresseProdutoCliente>()
				.name("lerInteresseProdutoClienteReader")
				.dataSource(dataSource)
				.sql("select * "
						+ "from interesse_produto_cliente i,"
						+ "		cliente c,"
						+ "		produto p"
						+ "where i.cliente = c.id and"
						+ "		 i.produto = p.id")
				.rowMapper(rowMapper())
				.build();
		
		
	}

	private RowMapper<InteresseProdutoCliente> rowMapper() {
		
		return new RowMapper<InteresseProdutoCliente>() {
			
			@Override
			public InteresseProdutoCliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Cliente cliente = new Cliente(rs.getInt("cliente_id"), rs.getString("cliente_nome"), rs.getString("emal"));
				Produto produto = new Produto(rs.getInt("produto_id"), rs.getString("produto_nome"), rs.getString("descricao"), rs.getDouble("preco"));
				InteresseProdutoCliente interesseProdutoCliente = new InteresseProdutoCliente(cliente, produto);
				
				
				return interesseProdutoCliente;
			}
		};
	}

}
