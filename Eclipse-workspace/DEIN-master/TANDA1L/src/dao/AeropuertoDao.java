 package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class AeropuertoDao {
	 private ConexionDB conexion;
	
	public ObservableList<Aeropuerto> cargarAeroPuerto(	String p) throws SQLException  {
        ObservableList<Aeropuerto> vuelos = FXCollections.observableArrayList();
        String sql;
        
        try {
            conexion = new ConexionDB();
            Connection con = conexion.getConexion();
            
            
            //AEROPUERTOS PRIVADOS
            if (p.equals("privado")) {
                sql = "SELECT * FROM aeropuertos a, direcciones d, aeropuertos_privados p WHERE id_direccion = d.id AND p.id_aeropuerto = a.id;";
            }
            //AEROPUERTOS PUBLICOS
            else {
                sql = "SELECT * FROM aeropuertos a, direcciones d, aeropuertos_publicos p WHERE id_direccion = d.id AND p.id_aeropuerto = a.id;";
            }
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (p.equals("privado")) {
                	int id=rs.getInt("a.id");
                	String nom= rs.getString("nombre");
                	String pais=rs.getString("pais");
                	String  ciudad=rs.getString("ciudad");
                	String calle=rs.getString("calle");
                	int num= rs.getInt("numero");
                	int anio=rs.getInt("anio_inauguracion");
                	int capacidad=rs.getInt("capacidad");
                	int numSocios= rs.getInt("numero_socios");
                	
                    AeropuertoPrivado v = new AeropuertoPrivado(id,nom, pais, ciudad, calle, num, anio, capacidad, numSocios);
                    vuelos.add(v);
                }else {
                	int id=rs.getInt("a.id");
                	String nom= rs.getString("nombre");
                	String pais=rs.getString("pais");
                	String  ciudad=rs.getString("ciudad");
                	String calle=rs.getString("calle");
                	int num= rs.getInt("numero");
                	int anio=rs.getInt("anio_inauguracion");
                	int capacidad=rs.getInt("capacidad");
                	double finan=rs.getDouble("financiacion");
                	int traba=rs.getInt("num_trabajadores");
                    AeropuertoPublico v = new AeropuertoPublico(id,nom, pais, ciudad, calle, num, anio, capacidad,finan,traba);
                    vuelos.add(v);
                }
            }
            rs.close();
            ps.close();
            
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vuelos;
    }

	//CREAR AEROPUERTO PRIVADO
	public boolean aniadirAeropuertoPriv(AeropuertoPrivado a) {
		
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
			//PRIMERO AÑADIR DIRECCIONES
			PreparedStatement pst = con.prepareStatement("insert into direcciones (pais,ciudad,calle,numero) values(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			//pst.setString(1, null);
			pst.setString(1, a.getPais());
			pst.setString(2, a.getCiudad());
			pst.setString(3, a.getCalle());
			pst.setInt(4, a.getNum());
			int n = pst.executeUpdate();

			int id = -1;

			if (n > 0) {

				ResultSet rs = pst.getGeneratedKeys();
	
				if (rs.next()) {
	
					id = rs.getInt(1);
	
				}
				rs.close();
	
				
				//CREAR AEROPUERTO
				String sql= "INSERT INTO aeropuertos  (nombre,anio_inauguracion,capacidad,id_direccion) VALUES(?,?,?,?)";
	
				pst = conexion.getConexion().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	
				
				pst.setString(1, a.getNombre());
				pst.setInt(2, a.getAño());
				pst.setInt(3, a.getCapacidad());
				pst.setInt(4, id);
				System.out.println(pst.toString());
				n = pst.executeUpdate();
				id = -1;

				if (n > 0) {

					 rs = pst.getGeneratedKeys();
		
					if (rs.next()) {
		
						id = rs.getInt(1);
		
					}
					rs.close();
		
					
					
					
						sql= "INSERT INTO aeropuertos_privados (id_aeropuerto,numero_socios)  VALUES(?,?)";
						
						pst = conexion.getConexion().prepareStatement(sql);
			
						pst.setInt(1, id);
						pst.setInt(2, a.getNsocios());

					
					
		
					n = pst.executeUpdate();

				}
			}

			pst.close();

			conexion.cerrarConexion();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	//AEROPUERTOS PUBLICOS
	public boolean aniadirAeropuertoPu(AeropuertoPublico a) {
		
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
			//PRIMERO AÑADIR DIRECCIONES
			PreparedStatement pst = con.prepareStatement("insert into direcciones (id,pais,ciudad,calle,numero) values(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, null);
			pst.setString(1, a.getPais());
			pst.setString(2, a.getCiudad());
			pst.setString(3, a.getCalle());
			pst.setInt(4, a.getNum());
			int n = pst.executeUpdate();

			int id = -1;

			if (n > 0) {

				ResultSet rs = pst.getGeneratedKeys();
	
				if (rs.next()) {
	
					id = rs.getInt(1);
	
				}
				rs.close();
	
				
				//CREAR AEROPUERTO
				String sql= "INSERT INTO aeropuertos VALUES (nombre,anio_inaguracion,capacidad,id_direccion)(?,?,?,?,?)";
	
				pst = conexion.getConexion().prepareStatement(sql);
	
				pst.setString(1, null);
				pst.setString(2, a.getNombre());
				pst.setInt(3, a.getAño());
				pst.setInt(4, a.getCapacidad());
				pst.setInt(5, id);
	
				n = pst.executeUpdate();
				id = -1;

				if (n > 0) {

					 rs = pst.getGeneratedKeys();
		
					if (rs.next()) {
		
						id = rs.getInt(1);
		
					}
					rs.close();
		
					
					//CREAR AEROPUERTO PUBLICO O PRIVADO
					
						sql= "INSERT INTO aeropuertos_publicos VALUES (id_aeropuerto,financiacion,num_trabajadores)(?,?,?)";
						
						pst = conexion.getConexion().prepareStatement(sql);
			
						pst.setInt(1, id);
						pst.setDouble(2, a.getFinan());
						pst.setDouble(3, a.getTrabajadores());

					
					
		
					n = pst.executeUpdate();

				}
			}

			pst.close();

			conexion.cerrarConexion();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
