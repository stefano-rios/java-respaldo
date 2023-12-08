package entities;

public class Bebida {
	int id;
	float precio;
	String nombre;
	float litros;
	String foto;
	
	
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Bebida() {
		
	}
	
	public Bebida(float precio, String nombre, float litros) {

		this.precio = precio;
		this.nombre = nombre;
		this.litros = litros;
	}
	
	public Bebida(float precio, String nombre, float litros, String foto) {

		this.precio = precio;
		this.nombre = nombre;
		this.litros = litros;
		this.foto = foto;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getLitros() {
		return litros;
	}
	public void setLitros(float f) {
		this.litros = f;
	}
	
	@Override
	public String toString() {
		return "\n Bebida [id=" + id + ", precio=" + precio + ", nombre=" + nombre + ", litros=" + litros
				+ "]";
	}
	
}