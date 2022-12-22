package ejercicioF;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class EjercicioF extends Application{
	private TextField txtNombre,txtApellido,txtEdad,txtBuscar;
	private TextField txtNombreM,txtApellidoM,txtEdadM;
	private Button btnNuevo,btnCancelar,btnAgregar,btnModificar,btnEliminar,btnExportar,btnImportar;
	private ObservableList<Persona> listPersonas,listaFiltrada;
	private TableView<Persona> tablaPersona;
	private int fallos,suma;
	@Override
	public void start(Stage primaryStage){
		try {
			//DECLARANDO LOS ELEMENTOS
				//botones
			btnAgregar=new Button("Agregar Persona");
			btnModificar=new Button("Modificar Persona");
			btnEliminar=new Button("Eliminar Persona");
			btnExportar=new Button("Exportar");
			btnImportar=new Button("Importar");
				//lista en la que se basa la tabla
			listPersonas=FXCollections.observableArrayList();
				//la tabla de personas
			tablaPersona=new TableView<>(listPersonas);
				//el textfield para buscar gente en la lista
			txtBuscar=new TextField();
			txtBuscar.setOnKeyTyped(e->filtrar(e));
			//*******************************************
			//CREANDO LA PESTAÑA
			 GridPane root=new GridPane();
			 	//Primer apartado
			 FlowPane fpnombre=new FlowPane(10,0);
			 fpnombre.getChildren().addAll(new Label("Filtrar por nombre"),txtBuscar,btnExportar,btnImportar);
			 fpnombre.setPadding(new Insets(0,0,10,0));
			 
			 	//Segundo Apartado
			 TableColumn<Persona, String> colNombre=new TableColumn<>("NOMBRE");
			 colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			 colNombre.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.3333));
			 
			 TableColumn<Persona, String> colApellido=new TableColumn<>("APELLIDOS");
			 colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
			 colApellido.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.3333));
			 
			 TableColumn<Persona, String> colEdad=new TableColumn<>("EDAD");
			 colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
			 colEdad.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.15));
			 colEdad.setStyle("-fx-alignment: CENTER-RIGHT;");
			 
			 tablaPersona.getColumns().addAll(colNombre,colApellido,colEdad);
			 colNombre.setMinWidth(200);
			 colApellido.setMinWidth(200);
			 colEdad.setMinWidth(100);
			 //**********************************
			 FlowPane fp= new FlowPane(40,40);
			 fp.getChildren().add(btnAgregar);
			 fp.getChildren().add(btnModificar);
			 fp.getChildren().add(btnEliminar);
			 fp.setAlignment(Pos.CENTER);
			 fp.setPadding(new Insets(10,0,0,0));
			 //AGREGAR
			 root.add(fpnombre, 0, 0,1,1);
			 root.add(tablaPersona,0,1,1,1);
			 root.add(fp, 0, 2,1,1);
			 
			 root.setStyle("-fx-padding: 10;");
			//*******EVENTOS***********
			 btnAgregar.setOnAction(e->nuevo(primaryStage));
			 btnModificar.setOnAction(e-> ModificarPers(primaryStage,primaryStage));
			 btnEliminar.setOnAction(e->EliminarAlert(primaryStage));
	         btnExportar.setOnAction(e->Exportar(primaryStage));
	         btnImportar.setOnAction(e->Importar(primaryStage));
			 //PARA QUE SE VEA
			 ColumnConstraints c1=new ColumnConstraints();
			 c1.setHgrow(Priority.ALWAYS);
			 root.getColumnConstraints().add(c1);
			 root.setVgrow(tablaPersona, Priority.ALWAYS);
			 root.setVgrow(fp, Priority.ALWAYS);
			 
			 
//			 String imagePath = getClass().getResource("/picture/agenda.png").toString();
//			 primaryStage.getIcons().add(new Image(imagePath));
			 Scene scene = new Scene(root);
			 primaryStage.setScene(scene);
			 primaryStage.setTitle("PERSONAS");
			 primaryStage.show();
			 primaryStage.sizeToScene();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void Exportar(Stage st) {
		FileChooser FC=new FileChooser();
		File archivo=FC.showSaveDialog(st);
		try {
			PrintWriter bw=new PrintWriter(archivo);
			String stri="Nombre,Apellido,Edad \n";
			for (int i = 0; i < tablaPersona.getItems().size(); i++) {
				System.out.println(tablaPersona.getItems().get(i).getNombre());
				stri+=tablaPersona.getItems().get(i).getNombre()+","+tablaPersona.getItems().get(i).getApellido()+","+tablaPersona.getItems().get(i).getEdad()+"\n";
				
			}
			bw.write(stri);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Importar(Stage st) {
		//PARA QUE ME SALGA EL NAVEGADOR DE ARCHIVOS
		FileChooser FC=new FileChooser();
		FC.setTitle("Elige el CSV");
		FC.setInitialDirectory(new File("/home/dm2/Escritorio"));
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
			BR.close();
			tablaPersona.refresh();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void filtrar(KeyEvent e) {
		String nom="";
		listaFiltrada=FXCollections.observableArrayList();
		nom=txtBuscar.getText();
		for (int i = 0; i < listPersonas.size(); i++) {
			if(listPersonas.get(i).getNombre().contains(nom)) {
				listaFiltrada.add(listPersonas.get(i));
				
			}
		}
		tablaPersona.refresh();
		tablaPersona.setItems(listaFiltrada);
		if(nom.isEmpty()){
			tablaPersona.setItems(listPersonas);
		}
		
	}
	public boolean añadir() {
		Persona p=new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
		if(listPersonas.contains(p)==true) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public String comprobar() {
		String mal="";
		fallos=0;
		
		//NOMBRE
		if (txtNombre.getText().length()==0) {
			mal+="El campo nombre es obligatorio";
			fallos++;
		}
		//APELLIDO
		if (txtApellido.getText().length()==0) {
			mal+="\n El campo apellido es obligatorio";
			fallos++;
		}
		if (txtEdad.getText().length()==0) {
			mal+="\n El campo edad es obligatorio rellenado";
			fallos++;
		}
		try {
			int num=Integer.parseInt(txtEdad.getText());
		} catch (NumberFormatException e) {
			if (txtEdad.getText().length()!=0) {
				mal+="\n El campo edad tiene que ser numeros";
				fallos++;
			}
		}
		if (this.añadir()==true) {
			mal+="\n ya existe esta persona en la tabla";
			fallos++;
		}
		
		if(fallos>0) {
			return mal;
		}else {

			return "Persona añadida correctamente";
			
		}
	}
	private void mostrarAlertError(Window win,Stage st) {
		Alert alert;
		String texto=this.comprobar();
		if (fallos>0) {
			alert = new Alert(Alert.AlertType.ERROR);
		}else {
			alert = new Alert(Alert.AlertType.INFORMATION);
			Persona p=new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
			listPersonas.add(p);
			st.close();
		}
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setContentText(texto);
		alert.setTitle("TUS DATOS");
		
		alert.showAndWait();
	}
    public void nuevo(Window win) {
    	GridPane root=new GridPane();
    	//DECLARAR
    	//-----Labels-----
    	Label lblnom=new Label("Nombre");
    	Label lblape=new Label("Apellido");
    	Label lbledad=new Label("Edad");
    	//-----textFields-----
    	txtNombre=new TextField();
    	txtApellido=new TextField();
    	txtEdad=new TextField();
    	//-----Botones-----
    	btnNuevo=new Button("Guardar");
    	btnCancelar=new Button("Cancelar");
    	//AÑADIRLOS
    	//-----Primera Fila-----
    	root.add(lblnom, 0, 0);
    	root.add(txtNombre, 1, 0,1,1);
    	lblnom.setStyle("-fx-padding: 10;");
    	lblnom.setAlignment(Pos.CENTER_RIGHT);
    	//-----Segunda Fila-----
    	root.add(lblape, 0, 1,1,1);
    	root.add(txtApellido, 1, 1,1,1);
    	lblape.setStyle("-fx-padding: 10;");
    	lblnom.setAlignment(Pos.CENTER_RIGHT);
    	//-----Tercera Fila-----
    	root.add(lbledad, 0, 2);
    	root.add(txtEdad, 1, 2,1,1);
    	txtEdad.setPrefSize(10, 10);
    	txtEdad.setPrefWidth(50);
    	lbledad.setStyle("-fx-padding: 10;");
    	//-----Cuarta Fila-----
    	FlowPane fp= new FlowPane(40,40);
		fp.getChildren().add(btnNuevo);
		fp.getChildren().add(btnCancelar);
		fp.setAlignment(Pos.CENTER);
		fp.setPadding(new Insets(10,0,0,0));
		root.add(fp, 0, 3,2,1);
    	//EVENTOS
    	ColumnConstraints cc1=new ColumnConstraints();
    	cc1.setHalignment(HPos.RIGHT);
        root.getColumnConstraints().add(cc1);
        
        
    	root.setAlignment(Pos.CENTER_RIGHT);
    	root.setAlignment(Pos.CENTER);
    	root.setStyle("-fx-padding: 10;");
    	//PARA QUE SE VEA
    	Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        //*******
        btnNuevo.setOnAction(e->nuevoP(newStage,newStage));
        btnCancelar.setOnAction(e->newStage.close());
        //*******
        newStage.setScene(newScene);
        newStage.setTitle("Nuevo usuario");
        
        newStage.initOwner(win);
        newStage.initModality(Modality.APPLICATION_MODAL);
        
        newStage.setWidth(400);
        newStage.setHeight(200);
        newStage.setResizable(false);
        newStage.setMaximized(false);
        newStage.show();
        
    }
    public void nuevoP(Window win,Stage st) {
    	mostrarAlertError(win,st);
    	//st.close();
    	
    }
    public void ModificarPers(Stage st,Window win) {
    	if (tablaPersona.getSelectionModel().getSelectedItem()==null) {
    		this.Alert(st);
    	}else {
    		GridPane root=new GridPane();
        	//DECLARAR
        	//-----Labels-----
        	Label lblnom=new Label("Nombre");
        	Label lblape=new Label("Apellido");
        	Label lbledad=new Label("Edad");
        	//-----textFields-----
        	txtNombre=new TextField(tablaPersona.getSelectionModel().getSelectedItem().getNombre());
        	txtApellido=new TextField(tablaPersona.getSelectionModel().getSelectedItem().getApellido());
        	txtEdad=new TextField(tablaPersona.getSelectionModel().getSelectedItem().getEdad());
        	//-----Botones-----
        	btnNuevo=new Button("Guardar");
        	btnCancelar=new Button("Cancelar");
        	//AÑADIRLOS
        	//-----Primera Fila-----
        	root.add(lblnom, 0, 0);
        	root.add(txtNombre, 1, 0,1,1);
        	lblnom.setStyle("-fx-padding: 10;");
        	lblnom.setAlignment(Pos.CENTER_RIGHT);
        	//-----Segunda Fila-----
        	root.add(lblape, 0, 1,1,1);
        	root.add(txtApellido, 1, 1,1,1);
        	lblape.setStyle("-fx-padding: 10;");
        	lblnom.setAlignment(Pos.CENTER_RIGHT);
        	//-----Tercera Fila-----
        	root.add(lbledad, 0, 2);
        	root.add(txtEdad, 1, 2,1,1);
        	txtEdad.setPrefSize(10, 10);
        	txtEdad.setPrefWidth(50);
        	lbledad.setStyle("-fx-padding: 10;");
        	//-----Cuarta Fila-----
        	FlowPane fp= new FlowPane(40,40);
    		fp.getChildren().add(btnNuevo);
    		fp.getChildren().add(btnCancelar);
    		fp.setAlignment(Pos.CENTER);
    		fp.setPadding(new Insets(10,0,0,0));
    		root.add(fp, 0, 3,2,1);
        	//EVENTOS
        	ColumnConstraints cc1=new ColumnConstraints();
        	cc1.setHalignment(HPos.RIGHT);
        	
            root.getColumnConstraints().add(cc1);

            
            
        	root.setAlignment(Pos.CENTER_RIGHT);
        	root.setAlignment(Pos.CENTER);
        	root.setStyle("-fx-padding: 10;");
        	//PARA QUE SE VEA
        	Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            //*******
            btnNuevo.setOnAction(e->modificarAux(newStage,newStage));
            btnCancelar.setOnAction(e->newStage.close());
            //*******
            newStage.setScene(newScene);
            newStage.setResizable(false);
            
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setTitle(" usuario");
            

            newStage.initOwner(win);
            newStage.initModality(Modality.APPLICATION_MODAL);
            
            newStage.setWidth(400);
            newStage.setHeight(200);
            newStage.setMaximized(false);
            
            newStage.show();
    	}
    }
    public void modificarAux(Window w,Stage st) {
    	this.comprobar();
    	
    	if(fallos==0) {
    		this.ModificarAlert(w);
        	tablaPersona.getSelectionModel().getSelectedItem().setNombre(txtNombre.getText());
        	tablaPersona.getSelectionModel().getSelectedItem().setApellido(txtApellido.getText());
        	tablaPersona.getSelectionModel().getSelectedItem().setEdad(txtEdad.getText());
            st.close();
        	
    	}else {
        	Persona p=new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
    		this.ModificarAlert(w);
    	}
    	tablaPersona.refresh();
    }
	private void ModificarAlert(Window win) {
		Alert alert;
		String texto=this.comprobar();
		if(fallos==0) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Modificado correctamente");
		}else {
			alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(texto);
		}
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("TUS DATOS");
		alert.showAndWait();
	}
	private void Eliminar() {
		Persona perselect=new Persona(tablaPersona.getSelectionModel().getSelectedItem().getNombre(), tablaPersona.getSelectionModel().getSelectedItem().getApellido(), tablaPersona.getSelectionModel().getSelectedItem().getEdad());
		tablaPersona.getItems().remove(perselect);
		tablaPersona.refresh();
	}
	private void EliminarAlert(Window win) {
		if (tablaPersona.getSelectionModel().getSelectedItem()==null) {
    		this.Alert(win);
    	}else {
    		Alert alert;
    		
    		alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setContentText("Eliminado correctamente");

    		alert.setHeaderText(null);
    		alert.initOwner(win);
    		alert.setTitle("TUS DATOS");
    		Eliminar();
    		alert.showAndWait();
    	}
		
	}private void Alert(Window win) {
		Alert alert;
		
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Debe de selecionar una persona");

		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("ERROR");
		alert.showAndWait();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
