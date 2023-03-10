package mx.tecnm.itlp.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import mx.tecnm.itlp.models.Peliculas;
import mx.tecnm.itlp.models.Planes;

@Repository
public class PeliculasJDBC {
	@Autowired
	private JdbcTemplate conexion;
	
	public List<Peliculas> consultarpeli() {
		String sql= "SELECT * FROM peliculas";
		return conexion.query(sql, new PeliculasRM());
	}
	public void insertpeli (Peliculas pelicula) {
		
		String sql = "INSERT INTO peliculas"
				+ "(titulo, valoracion, duracion, idioma, fecha_lanzamiento, subtitulos, sinopsis, productora,"
				+ "tamano_descarga,categorias_id)"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";
				conexion.update(sql,pelicula.getTitulo(),pelicula.getValoracion(),
						pelicula.getDuracion(), pelicula.getIdioma(), pelicula.getFecha_lanzamiento(),
						pelicula.getSubtitulos(),pelicula.getSinopsis(),pelicula.getProductora(),
						pelicula.getTamano_descarga(),pelicula.getCategorias_id());
	}
	
	public void desactivapeli(int peliculaid) {
		String sql = "UPDATE peliculas SET activa = 0 WHERE id = ?";
		conexion.update(sql, peliculaid);
		
	}
	
}
