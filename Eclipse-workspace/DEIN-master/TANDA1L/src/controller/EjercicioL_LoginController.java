package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EjercicioL_LoginController {
	@FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;

    @FXML
    void abrirListado(ActionEvent event) {
    	try{
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/EjercicioL_Aeropuertos.fxml"));
            Parent root = loader1.load();
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.setTitle("AVIONES-AEROPUERTOS");
            newStage.show();
            Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
            alert.showAndWait();
        }
	
	
    }


}
