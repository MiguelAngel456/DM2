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

public class DeporteDao {
	private ConexionDB conexion;
	
	//PARA AÑDIR FILAS A LA TABLA DEPORTE
	public boolean anadirDeporte(Deporte d) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
        System.out.println(d);
		PreparedStatement pst = con.prepareStatement("insert into Deporte (nombre) values(?)");
		
		pst.setString(1, d.getDeporte());

		pst.execute();
		con.close();
		pst.close();
		bien=true;

		
		return bien;
	}
	

	
	public 	ObservableList<Deporte> sacarDeportes() throws SQLException {
		ObservableList<Deporte> arr=FXCollections.observableArrayList();
        String sql;
		
 
		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		sql="SELECT * FROM Deporte;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			//sacar datos del evento para la tabla
		int id=rs.getInt("id_deporte");
		String nom=rs.getString("nombre");
		//crear el evento
			Deporte dep=new Deporte(id,nom);
			arr.add(dep);
			
		}
		return arr;
	}
	public boolean modificarDeporte(Deporte d) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
      
    	PreparedStatement pst;
    	
		pst = con.prepareStatement("update Deporte set nombre=? where id_deporte= ?");
    	pst.setString(1, d.getDeporte());
    	pst.setInt(2, d.getId());
    	pst.execute();
    	con.close();
    	pst.close();
    	bien= true;
	
		return bien;
	}
	public boolean eliminarDeporte(Deporte dep) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
        EventoDao evD=new EventoDao();
    	PreparedStatement pst;

    	pst = con.prepareStatement( "SELECT * FROM Evento WHERE id_deporte= ? ;");
    	pst.setInt(1, dep.getId());
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
        	int id_Evento=rs.getInt("id_evento");
        	            	
        	pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_evento = ?);");
			pst.setInt(1, id_Evento);
	    	pst.execute();
    	}
        //*****************************************************************
		pst = con.prepareStatement("DELETE FROM Evento WHERE (id_deporte = ?);");
		pst.setInt(1, dep.getId());
    	pst.execute();
    	//*********************************************
    	pst = con.prepareStatement("DELETE FROM Deporte WHERE (id_deporte = ?);");
    	pst.setInt(1, dep.getId());
        pst.execute();
        
    	con.close();
    	pst.close();
    	bien=true;
	
		return false;
	}
	public int ultimoId() throws SQLException {
		int id=0;
		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		
		String sql = "SELECT max(id_deporte) FROM olimpiadas.Deporte;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	 id=rs.getInt("max(id_deporte)");
        }
       
        rs.close();
        ps.close();
        con.close();

		return id;
	}
}
