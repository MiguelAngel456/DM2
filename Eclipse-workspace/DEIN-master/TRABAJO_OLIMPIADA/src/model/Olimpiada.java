package model;

import java.util.Objects;

public class Olimpiada {


	private String nombre,temporada,ciudad;
	private int anio, id;

	
	public Olimpiada(int id,String nombre, int anio, String temporada, String ciudad) {
		this.nombre = nombre;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getAnio() {
		return anio;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getCiudad() {
		return ciudad;
	}
	@Override
	public String toString() {
		return nombre;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	//LO COMPARO SOLO CON EL NOMBRE PORQUE EN EL NOMBRE YA TIENE EL AÑO Y LA TEMPORADA
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Olimpiada other = (Olimpiada) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	
}
