package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deporte;
import model.Deportista;
import model.Equipos;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

public class ParticipacionDao {
	private ConexionDB conexion;
	//PARA RELLENAR LA TABLA CON LA BASE DE DATOS
	public ObservableList<Participacion> cargarParticipacion() throws SQLException   {
		ObservableList<Participacion> arrParticipacion=FXCollections.observableArrayList();
		String sql;
		 sql = "SELECT * FROM Participacion a, Evento e, Deportista o, Equipo d, Olimpiada ol, Deporte de  "
		 		+ "WHERE a.id_evento = e.id_evento AND a.id_deportista = o.id_deportista "
		 		+ "AND a.id_equipo = d.id_equipo AND ol.id_olimpiada = e.id_olimpiada AND de.id_deporte = e.id_deporte;";

		conexion = new ConexionDB();
        Connection con;
		con = conexion.getConexion();
		
		PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
         while (rs.next()) {
         	//sacar datos del deportista para la tabla
         	int id_deportista=rs.getInt("o.id_deportista");
        	String nom_Deportista=rs.getString("o.nombre");
         	String sexo_Deportista=rs.getString("o.sexo");
         	int peso_Deportista=rs.getInt("o.peso");
         	int altura_Deportista=rs.getInt("o.altura");
         	InputStream foto = rs.getBinaryStream("o.foto");
         	
         	//sacar datos del equipo para la tabla
         	int id_equipo=rs.getInt("d.id_equipo");
         	String nom_Equipo=rs.getString("d.nombre");
         	String iniciales=rs.getString("d.iniciales");
         	
         	//sacar datos del evento para la tabla
         	String nom_evento=rs.getString("e.nombre");
         	int id_evento=rs.getInt("id_evento");
         	
         	//sacar datos de la olimpiada para el evento
         	int id_Olimpiada=rs.getInt("id_olimpiada");
         	String nom_Olimpiada=rs.getString("ol.nombre");
         	int anio_ol=rs.getInt("ol.anio");
         	String temporada=rs.getString("ol.temporada");
         	String ciudad=rs.getString("ol.ciudad");
         	//sacar datos del deporte para el evento
         	int id_deporte=rs.getInt("de.id_deporte");
         	String nom_deporte=rs.getString("de.nombre");
         	
         	//sacar datos de la participacion para la tabla
         	int edad_Participacion=rs.getInt("a.edad");
         	String medalla_Participacion=rs.getString("a.medalla");
         	
         	//crear los Objetos
         	
         	Deporte depo=new Deporte(id_deporte, nom_deporte);
         	Olimpiada ol=new Olimpiada(id_Olimpiada, nom_Olimpiada, anio_ol, temporada, ciudad);
         	Evento ev=new Evento(id_evento, nom_evento, ol, depo);
         
         	Deportista dep=new Deportista(id_deportista, nom_Deportista, sexo_Deportista, peso_Deportista, altura_Deportista,foto);
         	Equipos eq=new Equipos(id_equipo,nom_Equipo, iniciales);
         	//crear el Participacion
         	Participacion par=new Participacion(dep, edad_Participacion, medalla_Participacion, eq, ev);
         	arrParticipacion.add(par);
         }
         rs.close();
         ps.close();
         con.close();
         
         
		
		return arrParticipacion;
	}
	public ObservableList<Participacion> FiltrarParticipacion(boolean inv, boolean veran) throws SQLException   {
		ObservableList<Participacion> arrParticipacion=FXCollections.observableArrayList();
		String sql;
		 sql = "SELECT * FROM Participacion a, Evento e, Deportista o, Equipo d, Olimpiada f "
		 		+ "WHERE e.id_olimpiada = f.id_olimpiada AND a.id_evento = e.id_evento AND a.id_deportista = o.id_deportista "
		 		+ "AND a.id_equipo = d.id_equipo;";
		 
		 
			conexion = new ConexionDB();
	        Connection con;
			con = conexion.getConexion();
			
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	        	
	        	
	        	String temp_evento=rs.getString("f.temporada");
	         	//sacar datos del deportista para la tabla
	        	
	        	int id_deportista=rs.getInt("o.id_deportista");
	         	String nom_Deportista=rs.getString("o.nombre");
	         	String sexo_Deportista=rs.getString("o.sexo");
	         	int peso_Deportista=rs.getInt("o.peso");
	         	int altura_Deportista=rs.getInt("o.altura");
	         	Deportista dep=new Deportista(id_deportista, nom_Deportista, sexo_Deportista, peso_Deportista, altura_Deportista);
	         	
	         	//sacar datos del equipo para la tabla
	         	int id_equipo=rs.getInt("d.id_equipo");
	         	String nom_Equipo=rs.getString("d.nombre");
	         	String iniciales=rs.getString("d.iniciales");
	         	Equipos eq= new Equipos(id_equipo, nom_Equipo, iniciales);
	         	
	         	//sacar datos del evento para la tabla
	         	int id_evento=rs.getInt("e.id_evento");
	         	String nom_evento=rs.getString("e.nombre");
	         	Evento ev=new Evento(id_evento, nom_evento);
	         	
	         	//sacar datos de la participacion para la tabla
	         	
	         	int edad_Participacion=rs.getInt("a.edad");
	         	String medalla_Participacion=rs.getString("a.medalla");
	         	

	         	//crear el Participacion
	         	if((inv==true && temp_evento.equals("Winter")) && veran==false) {
	         		Participacion par=new Participacion(dep, edad_Participacion, medalla_Participacion, eq, ev);
	         		arrParticipacion.add(par);
	         	}else {
	         		if((veran==true && temp_evento.equals("Summer")) && inv==false) {
	         			Participacion par=new Participacion(dep, edad_Participacion, medalla_Participacion, eq, ev);
	         			arrParticipacion.add(par);
		         	}else {
		         		if(veran==true && inv==true) {
		         			Participacion par=new Participacion(dep, edad_Participacion, medalla_Participacion, eq, ev);
		         			arrParticipacion.add(par);
		         		}

		         	}
	         	}
	         	

	         	
	         }
	        rs.close();
            ps.close();
        
       
        
        con.close();
		
		return arrParticipacion;
		
	}
	public boolean anadirParticipacion(Participacion p) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
		PreparedStatement pst = con.prepareStatement("insert into Participacion (id_deportista, id_evento, id_equipo, edad, medalla) values(?,?,?,?,?)");
		
		pst.setInt(1, p.getDep().getId_deportista());
		pst.setInt(2, p.getEv().getId_evento());
		pst.setInt(3, p.getEq().getId_equipo());
		pst.setInt(4, p.getEdad());
		pst.setString(5, p.getMedalla());
		
		
		pst.execute();
		con.close();
		pst.close();
		bien=true;
		
		return bien;
	}
	public boolean modificarParticipacion(Participacion p, int idEvento_Antiguo, int idDeportista_Antiguo) throws SQLException {
		boolean bien=false;
		
		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		System.out.println(idEvento_Antiguo);
		PreparedStatement pst = con.prepareStatement("update Participacion set id_deportista=?, id_evento=?, id_equipo=?, edad=?, medalla=? where id_evento= ? "
													+ "AND id_deportista= ?;");
		pst.setInt(1, p.getDep().getId_deportista());
    	pst.setInt(2, p.getEv().getId_evento());
    	pst.setInt(3, p.getEq().getId_equipo());
    	pst.setInt(4, p.getEdad());
    	pst.setString(5, p.getMedalla());
    	
    	pst.setInt(6, idEvento_Antiguo);
    	pst.setInt(7, idDeportista_Antiguo);
    	pst.execute();
    	bien=true;
        
		return bien;
		
	}
	public boolean eliminarParticipacion(Participacion p) throws SQLException {
		boolean bien=false;

		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		PreparedStatement pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_evento = ?) AND (id_deportista = ?);");

		pst.setInt(1, p.getEv().getId_evento());
		pst.setInt(2, p.getDep().getId_deportista());
    	pst.execute();
    	bien=true;
	
		return bien;
	}
}
