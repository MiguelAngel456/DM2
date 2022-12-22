package model;

import java.util.Objects;

public class Equipos {
	private String nombre,iniciales;
	private int id_equipo;
	
	public Equipos(int id_equipo,String nombre, String iniciales) {
		this.nombre = nombre;
		this.iniciales = iniciales;
		this.id_equipo=id_equipo;
	}
	public Equipos(int id_equipo,String nombre) {
		this.nombre = nombre;
		this.id_equipo=id_equipo;
	}
	public String toString() {
		return nombre;
	}
	
	public int getId_equipo() {
		return id_equipo;
	}
	public String getNombre() {
		return nombre;
	}

	public String getIniciales() {
		return iniciales;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	//COMPARA ATRAVES DEL NOMBRE PORQUE NO HAY DOS EQUIPOS(PAISES) CON EL MISMO NOMBRE
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipos other = (Equipos) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
