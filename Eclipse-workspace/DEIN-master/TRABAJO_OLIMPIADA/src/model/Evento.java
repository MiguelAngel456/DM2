package model;

import java.util.Objects;

public class Evento{
	private String nom_Evento;
	
	private Olimpiada ol;
	
	private Deporte dep;
	
	private int id_evento;
	
	//CONTRUCTOR PARA LOS EVENTOS
	public Evento(int id_evento, String nom_Evento,Olimpiada ol, Deporte dep) {
		this.nom_Evento=nom_Evento;
		
		this.ol=ol;
		
		this.dep=dep;
		
		this.id_evento=id_evento;
	}

	public Evento(int id_evento, String nom_Evento) {
		this.id_evento=id_evento;
		this.nom_Evento=nom_Evento;
	}
	
	public String toString() {
		return nom_Evento+" "+dep.getDeporte()+" año: "+ol.getAnio();
	}
	
	public int getId_evento() {
		return id_evento;
	}
	public String getNom_Evento() {
		return nom_Evento;
	}
	//****************************************
	//Geters de Olimpiadas
	public Olimpiada getOl() {
		return ol;
	}
	public String getOlNombre() {
		return ol.getNombre();
	}
	public int getOlAnio() {
		return ol.getAnio();
	}
	public String getOlTemporada() {
		return ol.getTemporada();
	}
	public String getOlCiudad() {
		return ol.getCiudad();
	}
	//****************************************
	//Geters de Deporte
	public Deporte getDep() {
		return dep;
	}
	public String getDepNombre() {
		return dep.getDeporte();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom_Evento, ol);
	}
	//LO COMPARO POR EL NOMBRE Y LA OLIMPIADA Y NO TAMBIEN POR EL DEPORTE PORQUE EN EL NOMBRE YA VIENE EL DEPORTE 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(nom_Evento, other.nom_Evento) && Objects.equals(ol, other.ol);
	}




	
	
	
	

	
	
	
}
