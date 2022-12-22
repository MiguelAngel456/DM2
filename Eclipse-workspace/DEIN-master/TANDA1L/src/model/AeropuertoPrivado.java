package model;

public class AeropuertoPrivado extends Aeropuerto{
	private int Nsocios;
	public AeropuertoPrivado(int id, String nombre, String pais, String ciudad, String calle, int numero, int año,int capacidad,int Nsocios) {
		super(id, nombre, pais, ciudad, calle, numero, año, capacidad);
		this.Nsocios=Nsocios;
	}
	public int getNsocios() {
		return Nsocios;
	}
	public void setNsocios(int nsocios) {
		Nsocios = nsocios;
	}

	

}
