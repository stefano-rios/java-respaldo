package entities;

public class Plato {
	int id;
	String nombre;
	float precio;
	String descripcion;
	String foto;
	
	public Plato (String nombre, float precio, String descripcion) {
		
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
	}
	
	public Plato (String nombre, float precio, String descripcion, String foto) {
		
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.foto = foto;
	}
	
	public Plato() {
		
	}
	
	
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
		return "\n Bebida [id=" + id + ", nombre=" + nombre +  ", precio=" + precio
				+ "] descripcion = " + descripcion + "";
	}
}