package ejercicioC;

import java.util.Iterator;

import javafx.application.Application;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class EjercicioC extends Application{
	private TextField txtNombre,txtApellido,txtEdad;
	private Button btnAgregar,btnModificar,btnEliminar;
	private ObservableList<Persona> listPersonas;
	private TableView<Persona> tablaPersona;
	private int fallos,suma;
	@Override
	public void start(Stage primaryStage){
		try {
			//DECLARANDO LOS ELEMENTOS
				//textfield
			txtNombre=new TextField();
			txtApellido=new TextField();
			txtEdad=new TextField();
				//botones
			btnAgregar=new Button("Agregar Persona");
			btnModificar=new Button("Modificar");
			btnEliminar=new Button("Eliminar");
				//lista en la que se basa la tabla
			listPersonas=FXCollections.observableArrayList();
				//la tabla de personas
			tablaPersona=new TableView<>(listPersonas);
			//*******************************************
			//CREANDO LA PESTAÑA
			 GridPane root=new GridPane();
			 	//primer apartado: los datos de la persona y el boton de agregar
			 VBox datos=new VBox(10);
			 datos.getChildren().addAll(new Label("Nombre"),txtNombre);
			 datos.getChildren().addAll(new Label("Apellidos"),txtApellido);
			 datos.getChildren().addAll(new Label("Edad"),txtEdad);
			 
			 btnAgregar.setOnAction(e -> this.mostrarAlertError(primaryStage));
			 datos.getChildren().add(btnAgregar);
			 datos.setAlignment(Pos.CENTER_LEFT);
			 root.add(datos, 0,0);
			 	//Segundo apartado
			 TableColumn<Persona, String> colNombre=new TableColumn<>("NOMBRE");
			 colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			 colNombre.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.33));
			 
			 TableColumn<Persona, String> colApellido=new TableColumn<>("APELLIDOS");
			 colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
			 colApellido.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.33));
			 
			 TableColumn<Persona, String> colEdad=new TableColumn<>("EDAD");
			 colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
			 colEdad.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.33));
			 colEdad.setStyle("-fx-alignment: CENTER;");
			 
			 tablaPersona.getColumns().addAll(colNombre,colApellido,colEdad);
	
			 //******************
			 FlowPane fp=new FlowPane(40,40);
			 fp.getChildren().add(btnModificar);
			 fp.getChildren().add(btnEliminar);
			 root.add(fp, 0, 1,GridPane.REMAINING,1);
			 fp.setAlignment(Pos.CENTER);
			// fp.setPadding(new Insets(0,0,0,200));
			 //**********************************
			 datos.setStyle("-fx-padding: 10;");
			 root.setStyle("-fx-padding: 10;");
			 root.add(tablaPersona,1,0,2,1);
			 
			 //FUNCIONES
			 tablaPersona.setOnMouseClicked(e->seleccionarCampo());
			 btnModificar.setOnAction(e->modificar(primaryStage));
			 btnEliminar.setOnAction(e-> EliminarAlert(primaryStage));
			 //PARA QUE SE VEA
			 ColumnConstraints c1=new ColumnConstraints();
			 ColumnConstraints c2=new ColumnConstraints();
			
			 
			 c2.setHgrow(Priority.ALWAYS);
			 root.getColumnConstraints().add(c1);
			 root.getColumnConstraints().add(c2);
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
	private void modificar(Window win) {
		this.comprobar();
		if(fallos==0) {
			tablaPersona.getSelectionModel().getSelectedItem().setNombre(txtNombre.getText());
			tablaPersona.getSelectionModel().getSelectedItem().setApellido(txtApellido.getText());
			tablaPersona.getSelectionModel().getSelectedItem().setEdad(txtEdad.getText());
			this.ModificarAlert(win);
		}else {
			this.mostrarAlertError(win);
		}
		tablaPersona.refresh();
	}
	public void eliminar() {
		Persona perselect=new Persona(tablaPersona.getSelectionModel().getSelectedItem().getNombre(), tablaPersona.getSelectionModel().getSelectedItem().getApellido(), tablaPersona.getSelectionModel().getSelectedItem().getEdad());
		for (int i = 0; i < tablaPersona.getItems().size(); i++) {
			if (perselect.equals(tablaPersona.getItems().get(i))) {
				
				tablaPersona.getItems().remove(i);
			}
		}
		
		tablaPersona.refresh();
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
	private void seleccionarCampo() {
		txtNombre.setText(tablaPersona.getSelectionModel().getSelectedItem().getNombre());
		txtApellido.setText(tablaPersona.getSelectionModel().getSelectedItem().getApellido());
		txtEdad.setText(tablaPersona.getSelectionModel().getSelectedItem().getEdad());
	}
	
	private void ModificarAlert(Window win) {
		Alert alert;

		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("Modificado correctamente");

		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("TUS DATOS");
		alert.showAndWait();
		this.limpiar();
	}
	private void EliminarAlert(Window win) {
		Alert alert;
		
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("Eliminado correctamente");

		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("TUS DATOS");
		eliminar();
		alert.showAndWait();
		this.limpiar();
	}
	private void limpiar() {
		txtNombre.clear();
		txtApellido.clear();
		txtEdad.clear();
		tablaPersona.getSelectionModel().clearSelection();
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
		this.limpiar();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
