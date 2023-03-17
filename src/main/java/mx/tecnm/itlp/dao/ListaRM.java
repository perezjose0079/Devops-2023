package mx.tecnm.itlp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.itlp.models.Lista;
public class ListaRM implements RowMapper <Lista> {
	@Override
	public Lista mapRow(ResultSet rs, int rowNum) throws SQLException {
		Lista lista = new Lista();
		lista.setId(rs.getInt("id"));
		lista.setFecha(rs.getString("fecha"));
		lista.setPerfiles_usuarios_id(rs.getInt("perfiles_usuarios_id"));
		lista.setPeliculas_id(rs.getString("peliculas_id"));

		return lista;
	}
}
