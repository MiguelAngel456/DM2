package model;

public class Aeropuerto {
	private String nombre,pais,ciudad,calle;
	private int id,num,capacidad,año;
	
	public Aeropuerto(int id,String nombre,String pais,String ciudad,String calle,int num, int año,int capacidad) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.nombre=nombre;
		this.pais=pais;
		this.ciudad=ciudad;
		this.calle=calle;
		this.num=num;
		this.año=año;
		this.capacidad=capacidad;
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getPais() {
		return pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public int getId() {
		return id;
	}

	public int getNum() {
		return num;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getAño() {
		return año;
	}
	public int Socios(AeropuertoPrivado ap) {
		return ap.getNsocios();
	}
	
}
