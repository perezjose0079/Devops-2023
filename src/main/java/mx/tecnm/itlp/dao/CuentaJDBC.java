package mx.tecnm.itlp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.itlp.models.Cuentas;
import mx.tecnm.itlp.models.Planes;

@Repository
public class CuentaJDBC {
	@Autowired
	private JdbcTemplate conexion;
	
	public void insertar (Cuentas cuenta) {
		
		String sql = "INSERT INTO cuentas"
				+ "(email, password, activa, fecha_ultimo_pago, nombre, apellido,"
				+ "numero_tarjeta, fecha_vencimiento, codigo_seguridad, tipo_tarjeta, planes_id)"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
				conexion.update(sql,cuenta.getEmail(),cuenta.getPassword(),
						cuenta.getActiva(), cuenta.getFecha_ultimo_pago(), cuenta.getNombre(),
						cuenta.getApellido(),cuenta.getNumero_tarjeta(),cuenta.getFecha_vencimiento(),
						cuenta.getCodigo_seguridad(),cuenta.getTipo_tarjeta(),cuenta.getPlanes_id());
	}
		
	public void modificar (Cuentas cuenta, int cuentaid) {
		String sql = "UPDATE cuentas SET email = ?, password = ?,activa = ?, nombre = ?, apellido = ?,"
				+ " numero_tarjeta = ?, fecha_vencimiento = ?, codigo_seguridad = ?, tipo_tarjeta = ?,"
				+ " planes_id = ? WHERE id = ?";
		conexion.update(sql,cuenta.getEmail(),cuenta.getPassword(), cuenta.getActiva(),cuenta.getNombre(),
				cuenta.getApellido(),cuenta.getNumero_tarjeta(),cuenta.getFecha_vencimiento(),
				cuenta.getCodigo_seguridad(),cuenta.getTipo_tarjeta(), cuenta.getPlanes_id(),cuentaid);	
	}
	
	public void desactivaCuenta (int cuentaid) {
		String sql = "UPDATE cuentas SET activa = 0 WHERE id = ?";
		conexion.update(sql, cuentaid);
	}
	
	
	public Cuentas login(Cuentas cuenta) {
		
		String sql = "SELECT * FROM cuentas WHERE email = ? AND password = ?  ";
		return conexion.queryForObject(sql, new CuentaRM(), cuenta.getEmail(), cuenta.getPassword());

	}
	
	public Planes buscarPlan(int id){
		String sql = "select * from planes "
				+ " join cuentas c on c.planes_id=planes.id "
				+ "where c.id=? AND c.activa = 1";
		return conexion.queryForObject(sql,new PlanRM(),id);
	}
}
