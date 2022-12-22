package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Persona;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

public class EjercicioGController implements Initializable{
	@FXML
	private TableView tablaPersona;
	@FXML
	private TableColumn colNombre1;
	@FXML
	private TableColumn colApellido1;
	@FXML
	private TableColumn colEdad1;
	@FXML
	private Button btnAñadir;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnEliminar;
	@FXML
	private TextField txtBuscar;
	@FXML
	private Button btnExportar;
	@FXML
	private Button btnImportar;
	private ObservableList<Persona> listaPersona;

	// Event Listener on Button[#btnAñadir].onAction
	@FXML
	public void anadirPersona(ActionEvent event) {
		abrirVentana(1);
		
	}
	
	@FXML
	public void modificarPersona(ActionEvent event) {
		abrirVentana(0);
		
	}
	
	public void abrirVentana(int tipo) {
		//CREAR LA VENTANA AÑADIR PERSONA
		 try{
	         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NuevoUsuarioFXML.fxml"));
	         Parent root = loader.load();
	         Scene newScene = new Scene(root);
	         Stage newStage = new Stage();
	         NuevoUsuarioFXMLController control= loader.getController();
	         control.cargarLista(listaPersona);
	         newStage.initModality(Modality.APPLICATION_MODAL);
	         newStage.initOwner(this.btnAñadir.getScene().getWindow());
	         newStage.setScene(newScene);
	         if(tipo==1) {
	        	 newStage.setTitle("Nueva Persona");
		         tablaPersona.refresh();
		         newStage.showAndWait ();
		         if(control.comprobar().length()==0) {
		        	  Persona p= control.getP();
			          listaPersona.add(p);
			          tablaPersona.refresh();
			          
		         }
	         }
	         if(tipo==0) {
	        	 if (tablaPersona.getSelectionModel().getSelectedItem()==null) {
	         		this.Alert(btnModificar.getScene().getWindow());
	         	}else {
	         		 Persona p =(Persona)tablaPersona.getSelectionModel().getSelectedItem();
	         		 control.getTxtNombre().setText(p.getNombre());
			         control.getTxtApellido().setText(p.getApellido());
			         control.getTxtEdad().setText(p.getEdad());	    
	         		 
		        	 newStage.setTitle("Modificar Persona");
			         tablaPersona.refresh();
			         newStage.showAndWait ();
			         
			         if(control.comprobar().length()==0) {
			        	  p.setNombre(control.getTxtNombre().getText());
			        	  p.setApellido(control.getTxtApellido().getText());
			        	  p.setEdad(control.getTxtEdad().getText());
				          tablaPersona.refresh();
				          
			         }
	         	}
	         }

	   } catch (IOException e) {
	       Alert alert = new Alert(Alert.AlertType.ERROR);
	       alert.setHeaderText(null);
	       alert.setTitle("Error");
	       alert.setContentText(e.getMessage());
	       alert.showAndWait();
	   }
	}
	public void eliminar(ActionEvent event) {
		if (tablaPersona.getSelectionModel().getSelectedItem()==null) {
     		this.Alert(btnModificar.getScene().getWindow());
     	}else {
     		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NuevoUsuarioFXML.fxml"));
     		NuevoUsuarioFXMLController control= loader.getController();
     		
     		Persona perselect= (Persona) tablaPersona.getSelectionModel().getSelectedItem();
     		listaPersona.remove(perselect);
     		eliminarAlert(btnExportar.getScene().getWindow());

     		
     	}
    
    }
	public void filtrar(KeyEvent event) {
		String nom="";
		ObservableList<Persona> listaFiltrada= FXCollections.observableArrayList();
		listaFiltrada=FXCollections.observableArrayList();
		nom=txtBuscar.getText();
		for (int i = 0; i < listaPersona.size(); i++) {
			if(listaPersona.get(i).getNombre().contains(nom)) {
				listaFiltrada.add(listaPersona.get(i));
				
			}
		}
		tablaPersona.refresh();
		tablaPersona.setItems(listaFiltrada);
		if(nom.isEmpty()){
			tablaPersona.setItems(listaPersona);
		}
		
	}
	public void exportar(ActionEvent event) {
		Stage stage = (Stage) btnExportar.getScene().getWindow();
		this.ayudaExportar(stage);
	}
	public void importar(ActionEvent event) {
		Stage stage = (Stage) btnImportar.getScene().getWindow();
		this.ayudaImportar(stage);
	}
	public void ayudaExportar(Stage st) {
		FileChooser FC=new FileChooser();
		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
		FC.setInitialDirectory(new File(currentPath));
		File archivo=FC.showSaveDialog(st);
		try {
			PrintWriter bw=new PrintWriter(archivo);
			String str="Nombre,Apellido,Edad \n";
			for (int i = 0; i < tablaPersona.getItems().size(); i++) {
				Persona p =(Persona)tablaPersona.getItems().get(i);
				str+=p.getNombre()+","+p.getApellido()+","+p.getEdad()+"\n";
				
			}
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablaPersona.refresh();
	}
	public void ayudaImportar(Stage st) {
		//PARA QUE ME SALGA EL NAVEGADOR DE ARCHIVOS
		FileChooser FC=new FileChooser();
		FC.setTitle("Elige el CSV");
		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
		FC.setInitialDirectory(new File(currentPath));
		FC.getExtensionFilters().add(new ExtensionFilter("Archivo CSV", "*.csv", "*.csv"));
		FC.setSelectedExtensionFilter(FC.getExtensionFilters().get(0));
		File archivo=FC.showOpenDialog(st);
		//IMPORTAR LA GENTE
		try {
			BufferedReader BR=new BufferedReader(new FileReader(archivo));
			int fila=0;
			String linea=BR.readLine();
			while(linea!=null) {
				if(fila!=0) {
					String[] partes=linea.split(",");
					Persona p=new Persona(partes[0],partes[1],partes[2]);
					tablaPersona.getItems().add(p);
				}
				fila++;
				linea=BR.readLine();
			}
			System.out.println(tablaPersona.getItems().size());
			tablaPersona.refresh();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void Alert(Window win) {
		Alert alert;
		
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Debe de selecionar una persona");

		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("ERROR");
		alert.showAndWait();
	}
	public void eliminarAlert(Window win) {
		Alert alert;
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Eliminado correctamente");
		
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("ELIMINADO");
		alert.showAndWait();
	}
	// Event Listener on Button[#btnEliminar].onAction
	@FXML
	public void eliminarPersona(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txtBuscar].onAction
	@FXML
	public void buscar(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnExportar].onAction
	@FXML
	public void Exportar(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnImportar].onAction
	@FXML
	public void Importar(ActionEvent event) {
		// TODO Autogenerated
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listaPersona = FXCollections.observableArrayList();
//		Persona p = new Persona("a","b","5");
//		listaPersona.add(p);
		tablaPersona.setItems(listaPersona);
		colNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colNombre1.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.40));
		colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		colApellido1.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.40));
		colEdad1.setCellValueFactory(new PropertyValueFactory<>("edad"));
		colEdad1.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.20));
		//tablaPersona.getColumns().addAll(colNombre1,colApellido1,colEdad1);
		
		
	}
	public TableView getTablaPersona() {
		return tablaPersona;
	}

	public ObservableList<Persona> getListaPersona() {
		return listaPersona;
	}

}
