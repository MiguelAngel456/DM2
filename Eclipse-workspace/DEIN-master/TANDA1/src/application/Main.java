package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	private CheckBox CBDeporte;
	private ListView<String> listaDep;
	private ComboBox<String> CBedad;
	private RadioButton RBHombre,RBMujer,RBOtro;
	private TextField txtProfesion,txtHermanos;
	private ObservableList<String> listEdades;
	private ObservableList<String> listDeportes;
	private Slider s1,s2,s3;
	private int fallo;
	@Override
	public void start(Stage primaryStage) {
		try {
			//CREAR EL GRIDPANEL
			GridPane root = new GridPane();
			//Declararlas las observableList
			listEdades=FXCollections.observableArrayList("Menores de 18","Entre 18 y 30","Entre 31 y 50","Entre 51 y 70","Mayores de 70");
			listDeportes=FXCollections.observableArrayList("Tenis","Futbol","Baloncesto","Natacion","Ciclismo","otro");
			//Crear las labels
			Label ltitulo=new Label("ENCUESTA");
			ltitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
			Label lProfesion=new Label("Profesion");
			Label lHermanos=new Label("Nº Hermanos");
			Label ledad=new Label("Edad");
			Label lSexo=new Label("Sexo:");
			Label lSatisfaccion=new Label("Marque del 1 al 10 su grado de satisfaccion");
			lSatisfaccion.setFont(Font.font("Arial", FontWeight.BLACK, 12));
			//Crear los TextField
			txtProfesion=new TextField();
			txtHermanos=new TextField();
			//crear el comboBox
			CBedad=new ComboBox<>(listEdades);
			//Declarar el chechBox
			 CBDeporte=new CheckBox("Practicas algun deporte");
			//Declarar y modificar lista 
			listaDep=new ListView<>(listDeportes);
			listaDep.setDisable(true);
			listaDep.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			listaDep.setPrefSize(200, 70);
			
			//crear el slider(barra de nivel
			s1= new Slider(0,10,5);
			s1.setShowTickMarks(true);
			s1.setShowTickLabels(true);
			s1.setMajorTickUnit(1);
			//********************
			s2= new Slider(0,10,5);
			s2.setShowTickMarks(true);
			s2.setShowTickLabels(true);
			s2.setMajorTickUnit(1);
			//********************
			s3= new Slider(0,10,5);
			s3.setShowTickMarks(true);
			s3.setShowTickLabels(true);
			s3.setMajorTickUnit(1);
			//crear los tooltip
			Tooltip ayudaCompras=new Tooltip("indica del 1 al 10 lo que te gusta ir de compras");
			ayudaCompras.install(s1, ayudaCompras);
			//****************************************
			Tooltip ayudaTelevision=new Tooltip("indica del 1 al 10 lo que te gusta ver la television");
			ayudaTelevision.install(s2, ayudaTelevision);
			//****************************************
			Tooltip ayudaCine=new Tooltip("indica del 1 al 10 lo que te gusta ir al cine");
			ayudaCine.install(s3, ayudaCine);
			//****************************************
			Tooltip ayudaDeporte=new Tooltip("indica el deporte que haces");
			ayudaDeporte.install(listaDep, ayudaDeporte);
			//crear los botos
			Button botonAceptar=new Button("Aceptar");
			botonAceptar.setPrefSize(200,15);
			Button botonCancelar=new Button("Cancelar");
			botonCancelar.setPrefSize(200,15);
			
			//metidendo los apartados al grid
				//primera fila
			root.add(ltitulo, 0, 0,GridPane.REMAINING, 1);
			root.setHalignment(ltitulo, HPos.CENTER);
				//Segunda fila
			root.add(lProfesion, 0, 1,1,1);
			root.add(txtProfesion,	1, 1,3,1);
				//Tercera fila
			root.add(lHermanos, 0, 2,1,1);
			root.add(txtHermanos, 1, 2,1,1);
			//***********************************	
			root.add(ledad, 2, 2,1,1);
			CBedad.getSelectionModel().selectFirst();
			root.add(CBedad, 3, 2, 1, 1);
			
				//cuarta fila
			RBHombre=new RadioButton("Hombre");
			RBMujer=new RadioButton("Mujer");
			RBOtro=new RadioButton("Otro");
			RBOtro.setSelected(true);
			ToggleGroup group = new ToggleGroup();
			group.getToggles().addAll(RBHombre,RBMujer,RBOtro);
			HBox HSexo=new HBox(10,RBHombre,RBMujer,RBOtro);
			HSexo.setSpacing(50);
			root.add(lSexo, 0, 3,1,1);
			root.add(HSexo, 1, 3,1,1);
				//quinta fila
			

			root.add(CBDeporte, 0, 4,2,1);
			VBox VDeportes=new VBox(new Label("¿Cual?"),listaDep);
			root.add(VDeportes, 2, 4,2,1);
			CBDeporte.selectedProperty().addListener(this::changed);
			
				//Sexta Fila
			root.add(lSatisfaccion, 1, 5,GridPane.REMAINING,1);
			root.setHalignment(lSatisfaccion, HPos.CENTER);
				//Septima Fila
			root.add(new Label("Compras"), 0, 8,1,1);
			root.add(s1, 1, 8,3,1);
			
				//Octava fila
			root.add(new Label("Ver TeleVision"), 0, 9,1,1);
			root.add(s2, 1, 9,3,1);
			
				//Novena fila
			root.add(new Label("ir al Cine"), 0, 10,1,1);
			root.add(s3, 1, 10,3,1);
				//ultima fila
			root.setHalignment(botonAceptar, HPos.CENTER);
			root.setHalignment(botonCancelar, HPos.CENTER);
			
			root.add(botonAceptar, 0, 11,2,1);
			root.add(botonCancelar, 2, 11,2,1);
			botonAceptar.setOnAction(e-> mostrarAlertError(primaryStage));
			botonCancelar.setOnAction(e -> Platform.exit());
			//PARA QUE SE AJUSTE
			ColumnConstraints c1=new ColumnConstraints();
			ColumnConstraints c2=new ColumnConstraints();
			ColumnConstraints c3=new ColumnConstraints();
			ColumnConstraints c4=new ColumnConstraints();
			c4.setHgrow(Priority.ALWAYS);
			root.getColumnConstraints().add(c1);
			root.getColumnConstraints().add(c2);
			root.getColumnConstraints().add(c3);
			root.getColumnConstraints().add(c4);

			//PARA QUE SE PUEDA VER
			root.setStyle("-fx-padding: 14;");
			root.setHgap(10);
			root.setVgap(15);
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
			primaryStage.setTitle("ENCUESTA");
			primaryStage.show();
			primaryStage.sizeToScene();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void changed(ObservableValue<? extends Boolean> observable,Boolean oldValue,Boolean newValue) {
		if (CBDeporte.isSelected()==true) {
			listaDep.setDisable(false);
		}else {
			listaDep.setDisable(true);
		}
	}
	public String Aceptar() {
		String err="";
		String correcto="";
		fallo=0;
		int hermanos=0;
		//*********************
		if (txtProfesion.getText().length()>0) {
			correcto+=txtProfesion.getText();
		}else {
			fallo=1;
			err=err+"Hay rellenar el campo profesion";
		}
		
		//*********************
		try {
			hermanos=Integer.parseInt(txtHermanos.getText());
			correcto+="\n"+"Nº de hermanos:"+hermanos;
		} catch (NumberFormatException e) {
			fallo++;
			err+="\nHay rellenar el campo de numero de hermanos con Numeros";
		}
		//*************************
		correcto+="\nEdad:"+CBedad.getSelectionModel().getSelectedItem();
		//*************************
		if (RBHombre.isSelected()==true) {
			correcto+="\n"+"Sexo: Hombre";
		}else if (RBMujer.isSelected()==true) {
			correcto+="\n"+"Sexo: Mujer";
		}else if (RBOtro.isSelected()==true) {
			correcto+="\n"+"Sexo: Otro";
		}
		//*************************
		if (CBDeporte.isSelected() && listaDep.getSelectionModel().getSelectedItems().isEmpty()) {
			err+="\n Tienes que indicar los deportes que practicas";
			fallo++;
		}else {
			if (CBDeporte.isSelected()) {
				correcto+="\n"+"Deportes que practicas";
				for (int i = 0; i <listaDep.getSelectionModel().getSelectedItems().size(); i++) {
					correcto+="\n"+"\t"+ listaDep.getSelectionModel().getSelectedItems().get(i);
				}
			}
		}
		//**************************
		correcto+="\n"+"Grado de aficion a las compras: "+s1.getValue();
		correcto+="\n"+"Grado de aficion a ver la television: "+s2.getValue();
		correcto+="\n"+"Grado de aficion a ir al cine: "+s3.getValue();
		if (fallo>0) {
			return err;
		}else {
			return correcto;
		}
	}
	private void mostrarAlertError(Window win) {
		Alert alert;
		this.Aceptar();
		if (fallo>0) {
			alert = new Alert(Alert.AlertType.ERROR);
		}else {
			alert = new Alert(Alert.AlertType.INFORMATION);
		}
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setContentText(this.Aceptar());
		alert.setTitle("TUS DATOS");
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}