package dao;

import java.io.FileInputStream;
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

public class DeportistaDao {
	
	
	
	
private ConexionDB conexion;
	
	//PARA AÑDIR FILAS A LA TABLA DEPORTE
	public boolean anadirDeporte(Deportista d) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
		PreparedStatement pst = con.prepareStatement("insert into Deportista (nombre,sexo,peso,altura,foto) values(?,?,?,?,?)");
		
		pst.setString(1, d.getNombre());
		pst.setString(2, d.getSexo());
		pst.setInt(3, d.getPeso());
		pst.setInt(4, d.getAltura());
		pst.setBlob(5, d.getFoto());
		
		pst.execute();
		con.close();
		pst.close();
		bien= true;

		
		
		
		return bien;
	}
	public 	ObservableList<Deportista> sacarDeportistas() throws SQLException {
		ObservableList<Deportista> arr=FXCollections.observableArrayList();
        String sql;
        conexion = new ConexionDB();
        Connection con = conexion.getConexion();
        sql="SELECT * FROM olimpiadas.Deportista;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	//sacar datos 
        	int id=rs.getInt("id_deportista");
        	String nom=rs.getString("nombre");
        	String sexo=rs.getString("sexo");
        	int peso=rs.getInt("peso");
        	int altura=rs.getInt("altura");
        	InputStream foto = rs.getBinaryStream("foto");
        	//crear el evento
        	Deportista dep=new Deportista(id,nom, sexo, peso, altura,foto);
        	arr.add(dep);
        	
        }
        return arr;
	}
	
	public boolean modificarDeportista(Deportista d) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
      
    	PreparedStatement pst;
    	
		pst = con.prepareStatement("update Deportista set nombre=?, sexo=?, peso=?, altura=?, foto=? where id_deportista= ?");
    	pst.setString(1, d.getNombre());
    	pst.setString(2, d.getSexo());
    	pst.setInt(3, d.getPeso());
    	pst.setInt(4, d.getAltura());
    	pst.setBlob(5, d.getFoto());
    	pst.setInt(6, d.getId_deportista());
    	pst.execute();
    	con.close();
    	pst.close();
    	bien= true;


		
		return bien;
	}
	public boolean eliminarDeportista(Deportista d) throws SQLException {
		boolean bien=false;
		conexion = new ConexionDB();
        Connection con = conexion.getConexion();
    	PreparedStatement pst;
    	String sql = "SELECT * FROM Participacion WHERE id_deportista= ? ;";
        pst = con.prepareStatement(sql);
        pst.setInt(1, d.getId_deportista());
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
        	pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_deportista = ?);");
			pst.setInt(1, d.getId_deportista());
	    	pst.execute();
        }
        //*****************************************************************
		pst = con.prepareStatement("DELETE FROM  Deportista WHERE (id_deportista = ?);");
		pst.setInt(1, d.getId_deportista());
    	pst.execute();
    	//*********************************************

        
    	con.close();
    	pst.close();
    	bien= true;

		return bien;
	}
	public int ultimoId() throws SQLException {
		int id=0;
	
		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		
		String sql = "SELECT max(id_deportista) FROM olimpiadas.Deportista;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	 id=rs.getInt("max(id_deportista)");
        }
       
        rs.close();
        ps.close();
        con.close();

		return id;
	}
	public InputStream sacarFoto(Deportista d) throws SQLException {
		InputStream i=null;
		
		conexion = new ConexionDB();
		Connection con = conexion.getConexion();
		
		String sql = "SELECT foto FROM olimpiadas.Deportista WHERE id_deportista = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, d.getId_deportista());
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	 i=rs.getBinaryStream("foto");
        }
       
        rs.close();
        ps.close();
        con.close();
        return i;
	}
}
