package entities;

import java.util.LinkedList;

public class Empleado {

	private String dni;
	private String nombre;
	private String turno;
	private String password;
	private Boolean habilitado;
	private LinkedList<Rol> roles = new LinkedList<Rol>();
	
	public Empleado() {
		
	}
	
	public Empleado (String dni, String nombre, String turno, String p){
		this.dni = dni;
		this.nombre = nombre;
		this.turno = turno;
		this.password = p;
	}
	
	public Empleado (String dni, String nombre, String turno, String p, LinkedList<Rol> r){
		this.dni = dni;
		this.nombre = nombre;
		this.turno = turno;
		this.password = p;
		this.roles = r;
	}
	
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	public void setPassword(String p) {
		this.password = p;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void addRol(Rol rol) {
		roles.add(rol);
	}
	
	public LinkedList<Rol> getColeccionRoles(){
		return roles;
		
	}
	
	public String getRoles() {
		
		String texto = "";
		
		for (Rol r : roles) {
			
			texto += r.getDesc();
		}
		
		return texto;
		
	}
	
	public Boolean getHabilitado() {
		return this.habilitado;
	}
	
	public void setHabilitado(Boolean b) {
		this.habilitado = b;
	}

	
	
}
