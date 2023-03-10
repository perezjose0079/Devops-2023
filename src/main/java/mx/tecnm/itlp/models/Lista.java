package mx.tecnm.itlp.models;

public class Lista {
private int id;
private String fecha;
private String perfiles_usuarios_id;
private String Peliculas_id;
private String titulo;
public int getId() {
	return id;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public void setId(int id) {
	this.id = id;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public String getPerfiles_usuarios_id() {
	return perfiles_usuarios_id;
}
public void setPerfiles_usuarios_id(String perfiles_usuarios_id) {
	this.perfiles_usuarios_id = perfiles_usuarios_id;
}
public String getPeliculas_id() {
	return Peliculas_id;
}
public void setPeliculas_id(String peliculas_id) {
	Peliculas_id = peliculas_id;
}


}
