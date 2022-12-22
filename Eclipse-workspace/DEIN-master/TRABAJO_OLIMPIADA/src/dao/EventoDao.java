package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deporte;
import model.Evento;
import model.Olimpiada;

public class EventoDao {
	private ConexionDB conexion;
	private DeporteDao dp;
	//PARA RELLENAR LA TABLA CON LA BASE DE DATOS
	public ObservableList<Evento> cargarEvento() throws SQLException   {
		ObservableList<Evento> arrEvento=FXCollections.observableArrayList();
        String sql;
            conexion = new ConexionDB();
            Connection con = conexion.getConexion();
        	//para buscar en la base de datos todos los eventos
            sql = "SELECT * FROM Evento a, Olimpiada o, Deporte d WHERE a.id_olimpiada = o.id_olimpiada AND a.id_deporte = d.id_deporte;";
                        
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	//sacar datos del evento para la tabla
            	int id_evento=rs.getInt("a.id_evento");
            	String nom_Evento=rs.getString("a.nombre");
            	
            	//sacar datos de la Olimpiada para la tabla
            	int id_Olimpiada=rs.getInt("o.id_olimpiada");
            	String nom_Olimpiada=rs.getString("o.nombre");
            	int anio=rs.getInt("o.anio");
            	String temporada=rs.getString("o.temporada");
            	String ciudad=rs.getString("o.ciudad");
            	
            	//sacar datos de deporte para la tabla
            	int id_deporte=rs.getInt("d.id_deporte");
            	String nom_deporte=rs.getString("d.nombre");
            	
            	//crear el evento
            	Olimpiada ol=new Olimpiada(id_Olimpiada,nom_Olimpiada, anio, temporada, ciudad);
            	Deporte dep=new Deporte(id_deporte,nom_deporte);
            	
            	Evento event=new Evento(id_evento, nom_Evento, ol, dep);
            	arrEvento.add(event);
            	
            }
            rs.close();
            ps.close();
        
       
        
            con.close();
        
		
		return arrEvento;
	}
	public boolean anadirEvento(Evento ev) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
		PreparedStatement pst = con.prepareStatement("insert into Evento (nombre, id_olimpiada ,id_deporte) values(?,?,?)");
		
		pst.setString(1, ev.getNom_Evento());
		pst.setInt(2, ev.getOl().getId());
		pst.setInt(3, ev.getDep().getId());

		pst.execute();
		bien=true;
		return bien;
		
	}
	public boolean modificarEvento(Evento ev) throws SQLException {
		boolean bien=false;

		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
        
    	PreparedStatement pst;
    	
    	
		pst = con.prepareStatement("update Evento set nombre=?, id_olimpiada=?, id_deporte=? where id_evento = ?");
    	pst.setString(1, ev.getNom_Evento());
    	pst.setInt(2, ev.getOl().getId());
    	pst.setInt(3, ev.getDep().getId());
    	pst.setInt(4, ev.getId_evento());
    	pst.execute();
    	con.close();
    	pst.close();
    	bien= true;
		return bien;
	}
	
	public boolean eliminarEvento(Evento ev) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		PreparedStatement pst ;
    	//******************
    	  String sql = "SELECT * FROM Participacion WHERE id_evento= ? ;";
    	  
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, ev.getId_evento());
          ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_evento = ?);");
			pst.setInt(1, ev.getId_evento());
	    	pst.execute();
    	}
    	pst= con.prepareStatement("DELETE FROM Evento WHERE (id_evento = ? );");
    	pst.setInt(1, ev.getId_evento());
    	pst.execute();
    	bien= true;
        
		return bien;
	}
	public int ultimoId() throws SQLException {
		int id=0;
		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		
		String sql = "SELECT max(id_evento) FROM olimpiadas.Evento ;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	 id=rs.getInt("max(id_evento)");
        }
       
        rs.close();
        ps.close();
        con.close();
		return id;
	}
}
