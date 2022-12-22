package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.DeporteDao;
import dao.DeportistaDao;
import dao.EquipoDao;
import dao.OlimpiadaDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Deporte;
import model.Deportista;
import model.Equipos;
import model.Evento;
import model.Olimpiada;

public class listaController implements Initializable{
	@FXML
    private Button btnAñadir;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Label lblTitulo;

    @FXML
    private ListView listObjetos;
    //DAOS
    private OlimpiadaDao olD;
    private EquipoDao eqD;
    private DeportistaDao depD;
    private DeporteDao dd;
    @FXML
    void Añadir(ActionEvent event) {
    	if(this.lblTitulo.getText().toLowerCase().equals("olimpiadas")) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirOlimpiada.fxml"));
    		Parent root;
    		try {
				root = loader.load();
				Scene newScene = new Scene(root);
				Stage newStage = new Stage();
				newStage.setResizable(false);
		        newStage.setMaximized(false);
				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				AñadirOlimpiadaController control = loader.getController();
				Olimpiada ol=(Olimpiada) listObjetos.getSelectionModel().getSelectedItem();
				newStage.initModality(Modality.APPLICATION_MODAL);
				String imagePath = getClass().getResource("/images/logo.png").toString();
				newStage.getIcons().add(new Image(imagePath));
				newStage.setScene(newScene);
				newStage.setTitle("Añadir Olimpiadas");
				newStage.showAndWait();
				this.listObjetos.setItems(olD.sacarOlimpiada());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				error("Error en la lectura del fxml");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				error("Error en el sql");
			}
			
    	}else {
    		if(this.lblTitulo.getText().toLowerCase().equals("equipos")) {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirEquipo.fxml"));
        		Parent root;
        		try {
    				root = loader.load();
    				Scene newScene = new Scene(root);
    				Stage newStage = new Stage();
    				newStage.setResizable(false);
    		        newStage.setMaximized(false);
    				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
    				AñadirEquipoController control = loader.getController();
    				Equipos eq=(Equipos) listObjetos.getSelectionModel().getSelectedItem();
    				newStage.initModality(Modality.APPLICATION_MODAL);
    				String imagePath = getClass().getResource("/images/logo.png").toString();
    				newStage.getIcons().add(new Image(imagePath));
    				newStage.setScene(newScene);
    				newStage.setTitle("Añadir Equipos");
    				newStage.showAndWait();
    				this.listObjetos.setItems(eqD.sacarEquipos());
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				error("Error en la lectura del fxml");
    			} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
    			
    		}else {
    			if(this.lblTitulo.getText().toLowerCase().equals("deportistas")) {
            		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirDeportista.fxml"));
            		Parent root;
            		try {
        				root = loader.load();
        				Scene newScene = new Scene(root);
        				Stage newStage = new Stage();
        				newStage.setResizable(false);
        		        newStage.setMaximized(false);
        				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        				AñadirDeportistaController control = loader.getController();
        				newStage.initModality(Modality.APPLICATION_MODAL);
        				String imagePath = getClass().getResource("/images/logo.png").toString();
        				newStage.getIcons().add(new Image(imagePath));
        				newStage.setScene(newScene);
        				newStage.setTitle("Añadir Deportistas");
        				newStage.showAndWait();
        				try {
							this.listObjetos.setItems(depD.sacarDeportistas());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							error("Error en el sql");
						}
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				error("Error en la lectura del fxml");
        			}
        			
        		}else {
        			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirDeporte.fxml"));
            		Parent root;
            		try {
        				root = loader.load();
        				Scene newScene = new Scene(root);
        				Stage newStage = new Stage();
        				newStage.setResizable(false);
        		        newStage.setMaximized(false);
        				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        				AñadirDeporteController control = loader.getController();
        				Deporte d=(Deporte) listObjetos.getSelectionModel().getSelectedItem();
        				newStage.initModality(Modality.APPLICATION_MODAL);
        				String imagePath = getClass().getResource("/images/logo.png").toString();
        				newStage.getIcons().add(new Image(imagePath));
        				newStage.setScene(newScene);
        				newStage.setTitle("Añadir Deportes");
        				newStage.showAndWait();
        				try {
							this.listObjetos.setItems(dd.sacarDeportes());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							error("Error en el sql");
						}
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				error("Error en la lectura del fxml");
        			}
        		}
    		}
    	}
    }
    @FXML
    void eliminar(ActionEvent event) {
    	if(listObjetos.getSelectionModel().getSelectedItem()!=null) {
    		if(this.lblTitulo.getText().toLowerCase().equals("olimpiadas")) {
        		try {
        			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        			alert.setHeaderText(null);;
        			alert.setTitle("Eliminar Evento");
        			alert.setContentText("Si eliminas esta Olimpiada se eliminara los eventos relacionados y por lo tanto tamibien las participaciones relacionadas a esos eventos\n¿Estas seguro?");
        			Optional<ButtonType> result=alert.showAndWait();
        			if(result.get()==ButtonType.OK) {
        	    		Olimpiada ol=(Olimpiada) listObjetos.getSelectionModel().getSelectedItem();
        	    		olD.eliminarOlimpiada(ol);
        				this.info();
        				this.listObjetos.setItems(olD.sacarOlimpiada());
        			}
        		}catch (SQLException e) {
    				this.error("Error en el sql");
    			}
        		
        	}else {
        		if(this.lblTitulo.getText().toLowerCase().equals("equipos")) {
            		try {
            			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            			alert.setHeaderText(null);;
            			alert.setTitle("Eliminar Evento");
            			alert.setContentText("Si eliminas este equipo se eliminara las participaciones relacionadas a ese equipo\n¿Estas seguro?");
            			Optional<ButtonType> result=alert.showAndWait();
            			if(result.get()==ButtonType.OK) {
            	    		Equipos eq=(Equipos) listObjetos.getSelectionModel().getSelectedItem();
            	    		eqD.eliminarEquipo(eq);
            				this.info();
            				this.listObjetos.setItems(eqD.sacarEquipos());
            			}
            		}catch (SQLException e) {
        				this.error("Error en el sql");
        			}
        		}else {
        			if(this.lblTitulo.getText().toLowerCase().equals("deportistas")) {
                		try {
                			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                			alert.setHeaderText(null);;
                			alert.setTitle("Eliminar Evento");
                			alert.setContentText("Si eliminas este deportista se eliminara las participaciones relacionadas a ese deportista\n¿Estas seguro?");
                			Optional<ButtonType> result=alert.showAndWait();
                			if(result.get()==ButtonType.OK) {
                	    		Deportista dep=(Deportista) listObjetos.getSelectionModel().getSelectedItem();
                	    		depD.eliminarDeportista(dep);
                				this.info();
                				this.listObjetos.setItems(depD.sacarDeportistas());
                			}
                		}catch (SQLException e) {
            				this.error("Error en el sql");
            			}
        			}else {
                		try {
                			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                			alert.setHeaderText(null);;
                			alert.setTitle("Eliminar Evento");
                			alert.setContentText("Si eliminas este deporte se eliminara los eventos relacionadas a ese deporte y por lo tanto tambie las participaciones en ese evento"
                								+ "\n¿Estas seguro?");
                			Optional<ButtonType> result=alert.showAndWait();
                			if(result.get()==ButtonType.OK) {
                	    		Deporte dep=(Deporte) listObjetos.getSelectionModel().getSelectedItem();
                	    		dd.eliminarDeporte(dep);
                				this.info();
                				this.listObjetos.setItems(dd.sacarDeportes());
                			}
                		}catch (SQLException e) {
            				this.error("Error en el sql");
            			}
        				
        				
        			}
        		}
        	}
    	}else {
    		error("Clicle en una fila de la lista");
    	}
    	
    }

    @FXML
    void modificar(ActionEvent event) {
    	if(listObjetos.getSelectionModel().getSelectedItem()!=null) {
    		if(this.lblTitulo.getText().toLowerCase().equals("olimpiadas")) {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirOlimpiada.fxml"));
        		Parent root;
        		try {
    				root = loader.load();
    				Scene newScene = new Scene(root);
    				Stage newStage = new Stage();
    				newStage.setResizable(false);
    		        newStage.setMaximized(false);
    				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
    				AñadirOlimpiadaController control = loader.getController();
    				Olimpiada ol=(Olimpiada) listObjetos.getSelectionModel().getSelectedItem();
    				System.out.println(ol.getTemporada());
    				control.rellenar(ol);
    				newStage.initModality(Modality.APPLICATION_MODAL);
    				String imagePath = getClass().getResource("/images/logo.png").toString();
    				newStage.getIcons().add(new Image(imagePath));
    				newStage.setScene(newScene);
    				newStage.setTitle("Modificar Olimpiadas");
    				newStage.showAndWait();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				this.error("Error en la lectura del fxml");
    			}
        		try {
					this.listObjetos.setItems(olD.sacarOlimpiada());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
    		}else {
    			if(this.lblTitulo.getText().toLowerCase().equals("equipos")) {
    				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirEquipo.fxml"));
            		Parent root;
            		try {
        				root = loader.load();
        				Scene newScene = new Scene(root);
        				Stage newStage = new Stage();
        				newStage.setResizable(false);
        		        newStage.setMaximized(false);
        				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        				AñadirEquipoController control = loader.getController();
        				Equipos eq=(Equipos) listObjetos.getSelectionModel().getSelectedItem();
        				control.rellenar(eq);
        				newStage.initModality(Modality.APPLICATION_MODAL);
        				String imagePath = getClass().getResource("/images/logo.png").toString();
        				newStage.getIcons().add(new Image(imagePath));
        				newStage.setScene(newScene);
        				newStage.setTitle("Modificar Equipos");
        				newStage.showAndWait();
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				this.error("Error en la lectura del fxml");
        			}
            		try {
						this.listObjetos.setItems(eqD.sacarEquipos());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						error("Error en el sql");
					}
    			}else {
    				if(this.lblTitulo.getText().toLowerCase().equals("deportistas")) {
    					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirDeportista.fxml"));
                		Parent root;
                		try {
            				root = loader.load();
            				Scene newScene = new Scene(root);
            				Stage newStage = new Stage();
            				newStage.setResizable(false);
            		        newStage.setMaximized(false);
            				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
            				AñadirDeportistaController control = loader.getController();
            				Deportista dep=(Deportista) listObjetos.getSelectionModel().getSelectedItem();
            				control.rellenar(dep);
            				newStage.initModality(Modality.APPLICATION_MODAL);
            				String imagePath = getClass().getResource("/images/logo.png").toString();
            				newStage.getIcons().add(new Image(imagePath));
            				newStage.setScene(newScene);
            				newStage.setTitle("Modificar Deportistas");
            				newStage.showAndWait();
            			} catch (IOException e) {
            				// TODO Auto-generated catch block
            				this.error("Error en la lectura del fxml");
            			}
                		try {
							this.listObjetos.setItems(depD.sacarDeportistas());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							error("Error en el sql");
						}
    				}else {
    					if(this.lblTitulo.getText().toLowerCase().equals("deportes")) {
        					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirDeporte.fxml"));
                    		Parent root;
                    		try {
                				root = loader.load();
                				Scene newScene = new Scene(root);
                				Stage newStage = new Stage();
                				newStage.setResizable(false);
                		        newStage.setMaximized(false);
                				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
                				AñadirDeporteController control = loader.getController();
                				Deporte dep=(Deporte) listObjetos.getSelectionModel().getSelectedItem();
                				control.rellenar(dep);
                				newStage.initModality(Modality.APPLICATION_MODAL);
                				String imagePath = getClass().getResource("/images/logo.png").toString();
                				newStage.getIcons().add(new Image(imagePath));
                				newStage.setScene(newScene);
                				newStage.setTitle("Modificar Deportes");
                				newStage.showAndWait();
                			} catch (IOException e) {
                				// TODO Auto-generated catch block
                				this.error("Error en la lectura del fxml");
                			}
                    		try {
    							this.listObjetos.setItems(dd.sacarDeportes());
    						} catch (SQLException e) {
    							// TODO Auto-generated catch block
    							error("Error en el sql");
    						}
    					}
    				}
    			}
        	}
    	}else {
    		this.error("Selecciona una fila de la lista para modificar");
    	}
    	
    	
    }

	
	@FXML
	public void cancelar(ActionEvent event) {
		// TODO Autogenerated
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	public void rellenar(String tipo) {
		try {
			if(tipo.toLowerCase().equals("olimpiada")) {
				this.listObjetos.setItems(olD.sacarOlimpiada());
				this.lblTitulo.setText("Olimpiadas");
			}else {
				if(tipo.toLowerCase().equals("equipo")) {
					this.listObjetos.setItems(eqD.sacarEquipos());
					this.lblTitulo.setText("Equipos");
				}else {
					if(tipo.toLowerCase().equals("deportistas")) {
						this.listObjetos.setItems(depD.sacarDeportistas());
						this.lblTitulo.setText("Deportistas");
					}else {
						this.listObjetos.setItems(dd.sacarDeportes());
						this.lblTitulo.setText("Deportes");
					}
				}
			}
		}catch (SQLException e) {
			// TODO: handle exception
			error("Error en el sql");
		}
		
	}
	public void error (String t) {
		Alert alert;
		String texto=t;
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(texto);
		alert.setHeaderText(null);
		alert.setTitle("ERROR");
		alert.showAndWait();
	}
	public void info() {
		Alert alert;
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("ACCION HECHA CORRECTAMENTE");
		alert.setHeaderText(null);
		alert.setTitle("INFO");
		alert.showAndWait();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		olD=new OlimpiadaDao();
		eqD=new EquipoDao();
		depD=new DeportistaDao();
		dd=new DeporteDao();
	}
}
