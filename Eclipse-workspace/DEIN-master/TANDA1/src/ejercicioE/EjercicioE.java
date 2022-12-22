package ejercicioE;


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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class EjercicioE extends Application{
	private TextField txtNombre,txtApellido,txtEdad;
	private TextField txtNombreM,txtApellidoM,txtEdadM;
	private Button btnNuevo,btnCancelar,btnAgregar,btnModificar,btnEliminar;
	private ObservableList<Persona> listPersonas;
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
				//lista en la que se basa la tabla
			listPersonas=FXCollections.observableArrayList();
				//la tabla de personas
			tablaPersona=new TableView<>(listPersonas);
			//*******************************************
			//CREANDO LA PESTAÑA
			 GridPane root=new GridPane();
			 	//Unico apartado
			 TableColumn<Persona, String> colNombre=new TableColumn<>("NOMBRE");
			 colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			 colNombre.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.3333));
			 
			 TableColumn<Persona, String> colApellido=new TableColumn<>("APELLIDOS");
			 colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
			 colApellido.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.3333));
			 
			 TableColumn<Persona, String> colEdad=new TableColumn<>("EDAD");
			 colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
			 colEdad.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.3333));
			 colEdad.setStyle("-fx-alignment: CENTER;");
			 
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
			 root.add(tablaPersona,0,0,1,1);
			 root.add(fp, 0, 2,1,1);
			 
			 root.setStyle("-fx-padding: 10;");
			//*******EVENTOS***********
			 btnAgregar.setOnAction(e->nuevo());
			 btnModificar.setOnAction(e-> ModificarPers(primaryStage));
			 btnEliminar.setOnAction(e->EliminarAlert(primaryStage));
	            
			 //PARA QUE SE VEA
			 ColumnConstraints c1=new ColumnConstraints();
			 c1.setHgrow(Priority.ALWAYS);
			 root.getColumnConstraints().add(c1);
			 root.setVgrow(tablaPersona, Priority.ALWAYS);
			 root.setVgrow(fp, Priority.ALWAYS);
			 
			 
			 String imagePath = getClass().getResource("/picture/agenda.png").toString();
			 primaryStage.getIcons().add(new Image(imagePath));
			 Scene scene = new Scene(root);
			 primaryStage.setScene(scene);
			 primaryStage.setTitle("PERSONAS");
			 primaryStage.show();
			 primaryStage.sizeToScene();
		} catch (Exception e) {
			// TODO: handle exception
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
	private void mostrarAlertError(Window win) {
		Alert alert;
		String texto=this.comprobar();
		if (fallos>0) {
			alert = new Alert(Alert.AlertType.ERROR);
		}else {
			alert = new Alert(Alert.AlertType.INFORMATION);
			Persona p=new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
			listPersonas.add(p);
		}
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setContentText(texto);
		alert.setTitle("TUS DATOS");
		
		alert.showAndWait();
	}
    public void nuevo() {
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
        newStage.setWidth(400);
        newStage.setHeight(200);
        newStage.setMaximized(false);
        newStage.close();
        newStage.show();
        
    }
    public void nuevoP(Window win,Stage st) {
    	mostrarAlertError(win);
    	st.close();
    	
    }
    public void ModificarPers(Stage st) {
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
            newStage.setTitle(" usuario");
            newStage.setWidth(400);
            newStage.setHeight(200);
            newStage.setMaximized(false);
            newStage.close();
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
        	Persona p=new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
        	System.out.println(p);
        	
    	}else {
        	Persona p=new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
        	System.out.println(p);
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
		for (int i = 0; i < tablaPersona.getItems().size(); i++) {
			if (perselect.equals(tablaPersona.getItems().get(i))) {
				
				tablaPersona.getItems().remove(i);
			}
		}
		
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
