package model;

import java.io.InputStream;
import java.util.Objects;

public class Deportista {
	private String nombre,sexo;
	private int peso,altura;
	private InputStream foto;
	private int id_deportista;

	public Deportista(int id_deportista,String nombre, String sexo, int peso, int altura) {
		this.id_deportista=id_deportista;
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
	}
	//CONSTRUCTOR CON FOTO
	public Deportista(int id_deportista, String nombre, String sexo, int peso, int altura, InputStream foto) {
		this.id_deportista=id_deportista;
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.foto=foto;
	}

	public String toString() {
		return nombre;
	}

	public int getId_deportista() {
		return id_deportista;
	}
	public String getNombre() {
		return nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public int getPeso() {
		return peso;
	}

	public int getAltura() {
		return altura;
	}
	
	public InputStream getFoto() {
		return foto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	//COMPARO POR NMBRE PORQUE NO HAY 2 DEPORTISTAS QUE TENGAN EL MISMO NOMBRE
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deportista other = (Deportista) obj;
		return Objects.equals(nombre, other.nombre);
	}


	
	
}
