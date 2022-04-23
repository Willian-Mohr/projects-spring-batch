package br.com.wohr.projectcreditcardbill.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import br.com.wohr.projectcreditcardbill.domain.CartaoCredito;
import br.com.wohr.projectcreditcardbill.domain.Cliente;
import br.com.wohr.projectcreditcardbill.domain.Transacao;

@Configuration
public class LerTransacoesReaderConfig {
	
	@Bean
	public JdbcCursorItemReader<Transacao> lerTransacoesReder(
			@Qualifier("appDataSource") DataSource dataSource){
		
		return new JdbcCursorItemReaderBuilder<Transacao>()
				.name("lerTransacoesReder")
				.dataSource(dataSource)
				.sql("select * from transacao join cartao_credito using (numero_cartao_credito) order by numero_cartao_credito")
				.rowMapper(rowMapper())
				.build();
	}

	private RowMapper<Transacao> rowMapper() {
		
		return new RowMapper<Transacao>() {

			@Override
			public Transacao mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Cliente cliente = new Cliente(rs.getInt("cliente"));
				
				CartaoCredito cartaoCredito = new CartaoCredito(rs.getInt("numero_cartao_credito"), cliente);
				
				Transacao transacao = new Transacao(rs.getInt("id"), cartaoCredito, rs.getString("descricao"), rs.getDouble("valor"), rs.getDate("data"));
				
				return transacao;
			}
		};
	}
}
