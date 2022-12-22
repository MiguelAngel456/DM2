package model;

public class AeropuertoPublico extends Aeropuerto{
	private double finan;
	private int trabajadores;
	public AeropuertoPublico(int id, String nombre, String pais, String ciudad, String calle, int numero, int año,int capacidad,double finan,int trabajadores) {
		super(id, nombre, pais, ciudad, calle, numero, año, capacidad);
		// TODO Auto-generated constructor stub
		this.finan=finan;
		this.trabajadores=trabajadores;
	}
	public double getFinan() {
		return finan;
	}
	public void setFinan(double finan) {
		this.finan = finan;
	}
	public int getTrabajadores() {
		return trabajadores;
	}
	public void setTrabajadores(int trabajadores) {
		this.trabajadores = trabajadores;
	}
	
	
	


}
