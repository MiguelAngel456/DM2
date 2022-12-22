package model;

import java.io.InputStream;
import java.util.Objects;

public class Productos {
	private String codigo, nombre;
	private float precio;
	private int disponible;
	private InputStream foto;
	 public enum DisponibleCategory {
	        Si, No, 
	    };
	
	public Productos(String codigo, String nombre, float precio, int disponible, InputStream foto) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.disponible = disponible;
		this.foto=foto;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public int getDisponible() {
		return disponible;
	}
	
	public InputStream getFoto() {
		return foto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Productos other = (Productos) obj;
		return Objects.equals(codigo, other.codigo);
	}
	public DisponibleCategory getDisponibleCategory() {
        if (disponible == 1) {
            return DisponibleCategory.Si;
        }else {
        	return DisponibleCategory.No;
        }
	}

}
