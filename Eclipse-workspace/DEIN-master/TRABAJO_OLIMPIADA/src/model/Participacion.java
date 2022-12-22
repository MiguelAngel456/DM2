package model;

import java.util.Objects;

public class Participacion {

	private String medalla;
	private int edad;

	
	private Deportista dep;
	
	private Equipos eq;
	
	private Evento ev;


	public Participacion(Deportista dep, int edad, String medalla, Equipos eq, Evento ev) {
		
		this.dep=dep;
		this.ev=ev;
		this.eq=eq;
		this.edad = edad;
		this.medalla = medalla;

	}
	//--------------------DEPORTISTAS------------------------
	public Deportista getDep() {
		return dep;
	}

	
	public String getNomDeportista() {
		return dep.getNombre();
	}
	public String getSexoDeportista() {
		return dep.getSexo();
	}
	public int getPesoDeportista() {
		return dep.getPeso();
	}
	public int getAlturaDeportista() {
		return dep.getAltura();
	}

	//--------------------EVENTO----------------------------
	
	public Evento getEv() {
			return ev;
	}
	public String getNomEvento() {
		return ev.getNom_Evento();
	}
	public String getOlEvento() {
		return ev.getOlNombre();
	}
	
	//--------------------EQUIPO---------------------------
	public Equipos getEq() {
		return eq;
	}

	public String getNombreEquipo() {
		return eq.getNombre();
	}
		
	//--------------------PARTICIPACION---------------------
	
	public int getEdad() {
		return edad;
	}
	public String getMedalla() {
		return medalla;
	}
	//--------------------EQUALS---------------------------
	@Override
	public int hashCode() {
		return Objects.hash(dep, ev);
	}
	//LO COMPARA POR DEPORTISTA Y POR EVENTO PORQUE SON LAS CLAVES PRIMARIAS
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participacion other = (Participacion) obj;
		return Objects.equals(dep, other.dep) && Objects.equals(ev, other.ev);
	}
	


	
}
