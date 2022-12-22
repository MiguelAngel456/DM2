package application;

import java.util.Locale;

import java.util.ResourceBundle;

import conexionBD.Propiedades;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
//				String idioma = Propiedades.getValor("idioma");
//				String region = Propiedades.getValor("region");
//				Locale.setDefault(new Locale(idioma, region));
//				ResourceBundle bundle = ResourceBundle.getBundle("idiomas/messages");
				GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/EjercicioL_Login.fxml"));
				Scene scene = new Scene(root);
				String imagePath = getClass().getResource("/images/avion.png").toString();
		        primaryStage.setResizable(false);
		        primaryStage.setMaximized(false);
				primaryStage.getIcons().add(new Image(imagePath));
				primaryStage.setTitle("AVIONES-LOGIN");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	    void cambiarAprivado(ActionEvent event) {
	    	
	    }

	    void cambiarApublico(ActionEvent event) {

	    }
		public static void main(String[] args) {
			launch(args);
		}
	}


