package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			// Crear un nodo VBox VBox 
			root = new VBox();
			// Crear un nodo Texto
			Text msg = new Text("Hola JavaFX");
			// Añadir el nodo Text a la VBox como nodo hijo
			Button exitBtn = new Button("Exit");
			root.getChildren().addAll(msg,exitBtn);
			//EVENTO CON EXPRESION LAMBA
			exitBtn.setOnAction(e -> Platform.exit());
			// Crear una escena
			Scene scene = new Scene(root, 300, 50);
			// Asignar una escena al escenario 
			primaryStage.setScene(scene);
			primaryStage.setTitle("HolaFX Application");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
