package dao;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.Productos;

public class ProductosDao {
	private ConexionDB conexion;
	
	
	 public ObservableList<Productos> cargarProductos() {
		  ObservableList<Productos> listP= FXCollections.observableArrayList();
	        try {
				conexion = new ConexionDB();
				Connection con = conexion.getConexion();
		        String sql = "select * from productos";
		        PreparedStatement ps = con.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            String codigo=rs.getString("codigo");
		            String nombre=rs.getString("nombre");
		            float precio=rs.getFloat("precio");
		            int disp=rs.getInt("disponible");
		            InputStream foto=rs.getBinaryStream("imagen");
		            Productos p = new Productos(codigo, nombre, precio, disp,foto);
		            listP.add(p);
		        }
		        rs.close();
		        ps.close();
		        con.close();
		        return listP;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return null;
		 
	 }
	public boolean aniadirProducto(Productos p) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
			
			PreparedStatement pst = con.prepareStatement("insert into productos (codigo,nombre,precio,disponible,imagen) values(?,?,?,?,?)");
			
			pst.setString(1, p.getCodigo());
			pst.setString(2, p.getNombre());
			pst.setFloat(3, p.getPrecio());
			pst.setInt(4, p.getDisponible());
			pst.setBlob(5, p.getFoto());
			
			pst.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean eliminarProducto(Productos p) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
	        
	    	PreparedStatement pst;
			pst = con.prepareStatement("delete from productos where codigo=?");
			pst.setString(1, p.getCodigo());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	 public void modificarProducto(Productos p) throws SQLException {
	    	conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
	    	PreparedStatement pst=con.prepareStatement("update productos set codigo=?, nombre=? , precio=? , disponible=?, imagen=? where codigo=?");
	    	pst.setString(1, p.getCodigo());
	    	pst.setString(2, p.getNombre());
	    	pst.setFloat(3, p.getPrecio());
	    	pst.setInt(4, p.getDisponible());
	    	pst.setBlob(5, p.getFoto());
	    	pst.setString(6, p.getCodigo());
	    	pst.execute();
	}

	 
}
