package mx.tecnm.itlp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.itlp.models.Lista;
import mx.tecnm.itlp.models.Peliculas;
import mx.tecnm.itlp.models.Planes;
@Repository
public class ListaJDBC  {
		@Autowired
		private JdbcTemplate conexion;
		
		public List<Lista> consultarList() {
			String sql= "select  count(*) from mi_lista";
			return conexion.query(sql, new ListaRM());
		}
		public int consultartodo(int perfiles_usuarios_id){
			String sql = "select  count(*) from mi_lista where perfiles_usuarios_id=?";
			return conexion.queryForObject(sql, Integer.class, perfiles_usuarios_id);
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
	

