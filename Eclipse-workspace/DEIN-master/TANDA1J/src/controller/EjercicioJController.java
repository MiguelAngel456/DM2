package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

public class EjercicioJController {
	@FXML
	private ImageView rojo;
	@FXML
	private ImageView azulClaro;
	@FXML
	private ImageView azulOscuro;
	@FXML
	private ImageView negro;
	@FXML
	private ImageView gris;
	@FXML
	private ImageView blanco;
	@FXML
	private ImageView grisOscuro;
	@FXML
	private ImageView amarillo;
	@FXML
	private ImageView luces;
	
	private int turnoLuces;

	// Event Listener on ImageView[#luces].onMouseClicked
	@FXML
	public void encenderApagar(MouseEvent event) {
		if(turnoLuces==0) {
			String imagePath = getClass().getResource("/images/lucesOn.png").toString();
			luces.setImage(new Image(imagePath));
			turnoLuces++;
		}else {
			String imagePath = getClass().getResource("/images/lucesOff.png").toString();
			luces.setImage(new Image(imagePath));
			turnoLuces--;
		}
	}
    @FXML
    void colorear(MouseEvent event) {
    

    }
}
