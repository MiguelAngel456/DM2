package dao;

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

public class EquipoDao {
private ConexionDB conexion;
	
	//PARA AÑDIR FILAS A LA TABLA DEPORTE
	public boolean anadirEquipo(Equipos e) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
	    Connection con = conexion.getConexion();
		PreparedStatement pst = con.prepareStatement("insert into Equipo (nombre,iniciales) values(?,?)");
			
		pst.setString(1, e.getNombre());
		pst.setString(2, e.getIniciales());

		pst.execute();
		con.close();
		pst.close();
		bien=true;

		return bien;
	}

	
	public 	ObservableList<Equipos> sacarEquipos() throws SQLException {
		ObservableList<Equipos> arr=FXCollections.observableArrayList();
        String sql;
            conexion = new ConexionDB();
            Connection con = conexion.getConexion();
            sql="SELECT * FROM Equipo;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	//sacar datos 
            	int id=rs.getInt("id_equipo");
            	String nom=rs.getString("nombre");
            	String ini=rs.getString("iniciales");

            	
            	//crear el evento
            	Equipos equip=new Equipos(id,nom, ini);
            	arr.add(equip);
            	
            }
        return arr;
	}

	public boolean modificarEquipo(Equipos d) throws SQLException {
		boolean bien=false;
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
	      
	    	PreparedStatement pst;
	    	
			pst = con.prepareStatement("update Equipo set nombre=?, iniciales=? where id_equipo = ?");
	    	pst.setString(1, d.getNombre());
	    	pst.setString(2, d.getIniciales());
	    	pst.setInt(3, d.getId_equipo());
	    	pst.execute();
	    	con.close();
	    	pst.close();
	    	bien= true;
		
		return bien;
	}
	public boolean eliminarEquipo(Equipos eq) throws SQLException {
		boolean bien=false;
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
	    	PreparedStatement pst;

	    	String sql = "SELECT * FROM Participacion WHERE id_equipo= ? ;";
            pst = con.prepareStatement(sql);
            pst.setInt(1, eq.getId_equipo());
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
            	pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_equipo = ?);");
            	pst.setInt(1, eq.getId_equipo());
    	    	pst.execute();
	    	}
            //*****************************************************************
	    	sql = "DELETE FROM Equipo WHERE (id_equipo = ?);";
            pst = con.prepareStatement(sql);
            pst.setInt(1, eq.getId_equipo());
            pst.execute();
            
	    	con.close();
	    	pst.close();
	    	bien= true;
		

		
		return bien;
	}
	public int ultimoId() throws SQLException {
		int id=0;
		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		
		String sql = "SELECT max(id_equipo) FROM olimpiadas.Equipo;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	 id=rs.getInt("max(id_equipo)");
        }
       
        rs.close();
        ps.close();
        con.close();
		return id;
	}
}
