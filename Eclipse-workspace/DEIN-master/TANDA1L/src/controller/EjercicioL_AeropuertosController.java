package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.AeropuertoDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Aeropuerto;

public class EjercicioL_AeropuertosController implements Initializable{
		
		@FXML
	    private TableColumn<?, ?> Nsocios;

	    @FXML
	    private TableColumn<?, ?> calleAeropuerto;

	    @FXML
	    private TableColumn<?, ?> capaciodad;

	    @FXML
	    private TableColumn<?, ?> ciudadAeropuerto;

	    @FXML
	    private TableColumn<?, ?> colFinanciacion;

	    @FXML
	    private ToggleGroup grupoTipo;

	    @FXML
	    private TableColumn<?, ?> idAeropuerto;

	    @FXML
	    private TableColumn<?, ?> nomAeropuerto;

	    @FXML
	    private TableColumn<?, ?> numTrabajadores;

	    @FXML
	    private TableColumn<?, ?> numeroDIreccion;

	    @FXML
	    private TableColumn<?, ?> paisAeropuerto;
	    @FXML
	    private TableColumn<?, ?> colAnio;

	    @FXML
	    private RadioButton rbPrivados;

	    @FXML
	    private RadioButton rbPublicos;

	    @FXML
	    private TableView<Aeropuerto> tablaAeropuertos;

	    @FXML
	    private TextField txtNombre;
	    
	    private ObservableList<Aeropuerto> listaAeropuerto;
	    private AeropuertoDao ad;

	    
	    @FXML
	    void AniadirAeropurto(ActionEvent event) {
	    	try{
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioL_Anadir_Aeropuerto.fxml"));
	            Parent rootA = loader.load();
	            Scene newScene = new Scene(rootA);
	            Stage newStage = new Stage();
	            newStage.setScene(newScene);
	            newStage.setTitle("AVIONES-AEROPUERTOS-AÑADIR AEROPUERTO");
	            newStage.show();
	            Stage myStage = (Stage) this.tablaAeropuertos.getScene().getWindow();
	            //myStage.close();
	            
	        } catch (IOException e) {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText(e.getMessage());
	            e.printStackTrace();
	            alert.showAndWait();
	        }
	    }
    @FXML
    void cambiarApublico(ActionEvent event) {	
    	if(!rbPrivados.isSelected()) {
    		Nsocios.setVisible(false);
    		colFinanciacion.setVisible(true);
    		numTrabajadores.setVisible(true);
    		try {
    			ad=new AeropuertoDao();
    			listaAeropuerto=ad.cargarAeroPuerto("publico");
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    	}
    	
    	else{
    		Nsocios.setVisible(true);
    		colFinanciacion.setVisible(false);
    		numTrabajadores.setVisible(false);
    		try {
    			ad=new AeropuertoDao();
    			listaAeropuerto=ad.cargarAeroPuerto("privado");
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	tablaAeropuertos.setItems(listaAeropuerto);
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		try {
			ad=new AeropuertoDao();
			listaAeropuerto=ad.cargarAeroPuerto("privado");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablaAeropuertos.setItems(listaAeropuerto);
		
		
		rbPrivados.setSelected(true);
		colFinanciacion.setVisible(false);
		numTrabajadores.setVisible(false);
		//ASIGNAR LAS COLUMNAS A LOS CAMPOS DE OBJETO CORRESPONDIENTE
		idAeropuerto.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomAeropuerto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		paisAeropuerto.setCellValueFactory(new PropertyValueFactory<>("pais"));
		ciudadAeropuerto.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
		calleAeropuerto.setCellValueFactory(new PropertyValueFactory<>("calle"));
		numeroDIreccion.setCellValueFactory(new PropertyValueFactory<>("num"));
		colAnio.setCellValueFactory(new PropertyValueFactory<>("año"));
		capaciodad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
		//Privado
		if(rbPrivados.isSelected()) {
			Nsocios.setCellValueFactory(new PropertyValueFactory<>("Nsocios"));
		}else {
			//Publico
			colFinanciacion.setCellValueFactory(new PropertyValueFactory<>("financiacion"));
			numTrabajadores.setCellValueFactory(new PropertyValueFactory<>("trabajadores"));
		}
		
		
		
		
		
		
		
		
	}

}
