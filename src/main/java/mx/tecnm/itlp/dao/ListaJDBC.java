package mx.tecnm.itlp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.itlp.models.Lista;
@Repository
public class ListaJDBC  {
		@Autowired
		private JdbcTemplate conexion;
		
		public List<Lista> consultarList() {
			String sql= "select m.id, m.fecha, m.perfiles_usuarios_id, peliculas_id, p.titulo\r\n"
					+ "from mi_lista m\r\n"
					+ "inner join peliculas p on p.id = m.peliculas_id";
			return conexion.query(sql, new ListaRM());
		}
		public void insertpeli (Lista lista) {
			
			String sql = "INSERT INTO mi_lista"
					+ "(fecha, perfiles_usuarios_id, peliculas_id)"
					+ "VALUES"
					+ "(?,?,?)";
					conexion.update(sql,lista.getFecha(),lista.getPerfiles_usuarios_id(),lista.getPeliculas_id());
		}
		
		public void desactivaList (int listaid) {
			String sql = "UPDATE mi_lista SET activo = 0 WHERE id = ?";
			conexion.update(sql, listaid);
		}
		}
	

