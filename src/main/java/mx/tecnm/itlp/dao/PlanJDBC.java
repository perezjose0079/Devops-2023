package mx.tecnm.itlp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.itlp.models.Cuentas;
import mx.tecnm.itlp.models.Planes;

@Repository
public class PlanJDBC {
	@Autowired
	private JdbcTemplate conexion;
	
	public List<Planes> consultar() {
		String sql= "SELECT * FROM planes";
		return conexion.query(sql, new PlanRM());
	}

	public Planes buscar(int id) {
		
		String sql="SELECT * FROM planes WHERE id=?";
		return conexion.queryForObject(sql, new PlanRM(), id);

	}
	
	
	public void insertar(Planes plan) {
		String sql = "INSERT INTO planes(descripcion, precio_mensual, calidad_video, resolucion,"
		+ " cantidad_dispositivos) VALUES (?, ?, ?, ?, ?)";
		conexion.update(sql, plan.getDescripcion(), plan.getPrecio_mensual(),
		plan.getCalidad_video(), plan.getResolucion(), plan.getCantidad_dispositivos());
		
	}
	public void modificar(Planes plan, int planid) {
		String sql= "UPDATE planes SET descripcion = ?, precio_mensual = ?, calidad_video = ?, "
				+ "resolucion = ?, cantidad_dispositivos = ? WHERE id=?";
		conexion.update(sql,plan.getDescripcion(),plan.getPrecio_mensual(),
				plan.getCalidad_video(),plan.getResolucion(),plan.getCantidad_dispositivos(),planid);
	}
	
	public List <Cuentas> buscarCuentas(int id){
		String sql = "select * from cuentas c"
				+ " join planes on c.planes_id=planes.id "
				+ "where planes.id=? and activa = 1";
		return conexion.query(sql, new CuentaRM(),id);
	}

}
