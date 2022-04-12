package br.com.wohr.projectjdbccursor.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import br.com.wohr.projectjdbccursor.dominio.Cliente;

@Configuration
public class JdbcCursorReaderConfig {
	
	@Bean
	public JdbcCursorItemReader<Cliente> jdbcCursorReader(
			@Qualifier("appDataSource") DataSource dataSource) {
		
		return new JdbcCursorItemReaderBuilder<Cliente>()
				.name("jdbcCursorReader")
				.dataSource(dataSource)
				.sql("select * from cliente")
				.rowMapper(rowMapper())
				.build();
		
	}

	private RowMapper<Cliente> rowMapper() {
		
		return new RowMapper<Cliente>() {

			@Override
			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				if (rs.getRow() <= 11) {
					
					throw new SQLException(String.format("Encerrando a execução - Cliente inválido %s", rs.getString("email")));
					
				} else {
				
					return clienteRowMapper(rs);
					
				}
				
			}

			private Cliente clienteRowMapper(ResultSet rs) throws SQLException {
			
				return new Cliente(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("idade"), rs.getString("email"));
				
			}
		
		};
	}
	
}
