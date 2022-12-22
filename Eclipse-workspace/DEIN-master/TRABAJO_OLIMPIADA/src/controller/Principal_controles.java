package controller;

import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import conexionBD.Propiedades;
import dao.DeporteDao;
import dao.DeportistaDao;
import dao.EquipoDao;
import dao.EventoDao;
import dao.OlimpiadaDao;
import dao.ParticipacionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Deporte;
import model.Deportista;
import model.Equipos;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

public class Principal_controles implements Initializable {
	@FXML
	private Button btnAnadir;
    @FXML
    private GridPane gp;
	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnModificar;

	@FXML
	private ComboBox<String> cbTabla;

	@FXML
	private CheckBox checkBoxInvierno;

	@FXML
	private CheckBox checkBoxVerano;
    @FXML
    private ImageView img;

	@FXML
	private TableColumn<?, ?> col1Posicion;

	@FXML
	private TableColumn<?, ?> col2Posicion;

	@FXML
	private TableColumn<?, ?> col3Posicion;

	@FXML
	private TableColumn<?, ?> col4Posicion;

	@FXML
	private TableColumn<?, ?> col5Posicion;

	@FXML
	private TableColumn<?, ?> col6Posicion;

	@FXML
	private TableColumn<?, ?> colAltura;

	@FXML
	private TableColumn<?, ?> colEdad;

	@FXML
	private TableColumn<?, ?> colEquipo;

	@FXML
	private TableColumn<?, ?> colEvento;

	@FXML
	private TableColumn<?, ?> colMedalla;

	@FXML
	private TableColumn<?, ?> colNomDeportista;

	@FXML
	private TableColumn<?, ?> colPeso;

	@FXML
	private TableColumn<?, ?> colSexo;

	@FXML
	private TableView<Evento> tablaEvento;

	@FXML
	private TableView<Participacion> tablaParticipacion;


	// MENUITEMS
	 @FXML
    private MenuItem GestionarDeporte;

    @FXML
    private MenuItem GestionarDeportista;

    @FXML
    private MenuItem GestionarEquipo;

    @FXML
    private MenuItem GestionarOlimpiada;
	@FXML
    private Menu menuDeportista;

    @FXML
    private MenuBar menu;

	private ObservableList<Evento> listEventos;
	private ObservableList<Participacion> listParticipacion;

	private EventoDao ed;
	private ParticipacionDao pd;
	private DeportistaDao dd;
	
	private ResourceBundle bundle;

	@FXML
	void anadir(ActionEvent event) {

		if (cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirParticipacion.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene newScene = new Scene(root);

				Stage newStage = new Stage();
		        newStage.setResizable(false);
		        newStage.setMaximized(false);
				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				AñadirParticipacionController control = loader.getController();

				newStage.initModality(Modality.APPLICATION_MODAL);
				String imagePath = getClass().getResource("/images/logo.png").toString();
				newStage.getIcons().add(new Image(imagePath));
				newStage.setScene(newScene);
				newStage.setTitle("Añadir Participacion");
				newStage.showAndWait();

				listParticipacion = pd.cargarParticipacion();

				tablaParticipacion.setItems(listParticipacion);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				error("Error en la lectura del fxml");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				error("Error en el sql");
			}
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirEvento.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene newScene = new Scene(root);
				Stage newStage = new Stage();
				newStage.setResizable(false);
		        newStage.setMaximized(false);
				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				AñadirController control = loader.getController();

				newStage.initModality(Modality.APPLICATION_MODAL);
				String imagePath = getClass().getResource("/images/logo.png").toString();
				newStage.getIcons().add(new Image(imagePath));
				newStage.setScene(newScene);
				newStage.setTitle("Añadir Evento");
				newStage.showAndWait();
				listEventos = ed.cargarEvento();
				tablaEvento.setItems(listEventos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				error("Erroor en la lectura del fxml");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				error("Error en el sql");
			}
		}

	}



	@FXML
	void eliminar(ActionEvent event) {
		
		try {
			if(cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
				if(tablaParticipacion.getSelectionModel().getSelectedItem()!=null) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);;
					alert.setTitle("Eliminar Evento");
					alert.setContentText("Si continuas se eliminara esta participacion\n¿Estas seguro?");
					Optional<ButtonType> result=alert.showAndWait();
					if(result.get()==ButtonType.OK) {
						
						Participacion p=tablaParticipacion.getSelectionModel().getSelectedItem();
						pd.eliminarParticipacion(p);
						this.info();
					}
				
				}else {
					error("Clicke en una fila para poder eliminarla");
				}
				
			}else {
				if(tablaEvento.getSelectionModel().getSelectedItem()!=null) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);;
					alert.setTitle("Eliminar Evento");
					alert.setContentText("Si eliminas este Evento se eliminara las participaciones relacionadas\n¿Estas seguro?");
					Optional<ButtonType> result=alert.showAndWait();
					if(result.get()==ButtonType.OK) {
						
						Evento ev=tablaEvento.getSelectionModel().getSelectedItem();
						ed.eliminarEvento(ev);
						this.info();
					}
				}else {
					error("Clicke en una fila para poder eliminarla");
				}
				
			}
			listParticipacion = pd.cargarParticipacion();
			tablaParticipacion.setItems(listParticipacion);
			listEventos = ed.cargarEvento();
			tablaEvento.setItems(listEventos);
		}catch (SQLException e) {
			error("Error en el sql");
		}
		

	}



	@FXML
	void modificar(ActionEvent event) {
		if(tablaEvento.getSelectionModel().getSelectedItem()!=null || tablaParticipacion.getSelectionModel().getSelectedItem()!=null) {
			if (cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirParticipacion.fxml"));
				Parent root;
			
				try {
					root = loader.load();
					Scene newScene = new Scene(root);

					Stage newStage = new Stage();
					newStage.setResizable(false);
			        newStage.setMaximized(false);
					newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
					AñadirParticipacionController control = loader.getController();
					
					Evento evento=tablaParticipacion.getSelectionModel().getSelectedItem().getEv();
					Participacion p=tablaParticipacion.getSelectionModel().getSelectedItem();
					
					control.rellenar(p);
					
					//
					newStage.initModality(Modality.APPLICATION_MODAL);
					String imagePath = getClass().getResource("/images/logo.png").toString();
					newStage.getIcons().add(new Image(imagePath));
					newStage.setScene(newScene);
					newStage.setTitle("Modificar Participacion");
					newStage.showAndWait();
					this.tablaParticipacion.setItems(pd.cargarParticipacion());
					this.tablaParticipacion.refresh();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					error("Error en la lectura del fxml");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirEvento.fxml"));
				Parent root;
				try {
					root = loader.load();
					Scene newScene = new Scene(root);
					Stage newStage = new Stage();
					newStage.setResizable(false);
			        newStage.setMaximized(false);
					newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
					AñadirController control = loader.getController();
					
					//
					
					Evento ev=tablaEvento.getSelectionModel().getSelectedItem();
					control.rellenar(ev);
					//
					newStage.initModality(Modality.APPLICATION_MODAL);
					String imagePath = getClass().getResource("/images/logo.png").toString();
					newStage.getIcons().add(new Image(imagePath));
					newStage.setScene(newScene);
					newStage.setTitle("Modificar Evento");
					newStage.showAndWait();
					listEventos = ed.cargarEvento();
					tablaEvento.setItems(listEventos);
					tablaEvento.refresh();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					error("Error en la lectura del fxml");
				}
			}

		}else {
			this.error("Clica en una de las filas de la tabla para modificar");
		}
		

	}

	@FXML
	void gestionDeporte(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		DeporteDao ded=new DeporteDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.setResizable(false);
	        newStage.setMaximized(false);
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.rellenar("Deportes");
			newStage.initModality(Modality.APPLICATION_MODAL);
			String imagePath = getClass().getResource("/images/logo.png").toString();
			newStage.getIcons().add(new Image(imagePath));
			newStage.setScene(newScene);
			newStage.setTitle("Gestionar Deportes");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			error("Error en la lectura del fxml");
		}
		try {
			listParticipacion = pd.cargarParticipacion();
			listEventos = ed.cargarEvento();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("Error en el sql");
		}
		tablaParticipacion.setItems(listParticipacion);
		
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void gestionDeportista(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		DeporteDao ded=new DeporteDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.setResizable(false);
	        newStage.setMaximized(false);
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.rellenar("Deportistas");
			newStage.initModality(Modality.APPLICATION_MODAL);
			String imagePath = getClass().getResource("/images/logo.png").toString();
			newStage.getIcons().add(new Image(imagePath));
			newStage.setScene(newScene);
			newStage.setTitle("Gestionar Deportistas");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			error("Error en la lectura del fxml");
		}
		try {
			listParticipacion = pd.cargarParticipacion();
			listEventos = ed.cargarEvento();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("Error en el sql");
		}
		tablaParticipacion.setItems(listParticipacion);

		tablaEvento.setItems(listEventos);
	}

	@FXML
	void gestionEquipo(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		EquipoDao ded=new EquipoDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.setResizable(false);
	        newStage.setMaximized(false);
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.rellenar("Equipo");
			String imagePath = getClass().getResource("/images/logo.png").toString();
			newStage.getIcons().add(new Image(imagePath));
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Gestionar Equipos");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			error("Error en la lectura del fxml");
		}
		try {
			listParticipacion = pd.cargarParticipacion();
			listEventos = ed.cargarEvento();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("Error en el sql");
		}
		tablaParticipacion.setItems(listParticipacion);
		
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void gestionOlimpiada(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.setResizable(false);
	        newStage.setMaximized(false);
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.rellenar("Olimpiada");
			newStage.initModality(Modality.APPLICATION_MODAL);
			String imagePath = getClass().getResource("/images/logo.png").toString();
			newStage.getIcons().add(new Image(imagePath));
			newStage.setScene(newScene);
			newStage.setTitle("Gestionar Olimpiadas");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			error("Error en la lectura del fxml");
		}
		try {
			listParticipacion = pd.cargarParticipacion();
			listEventos = ed.cargarEvento();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("Error en el sql");
		}
		tablaParticipacion.setItems(listParticipacion);
		
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void seleccionar(ActionEvent event) {
		try {
			if (cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
				ParticipacionDao pd = new ParticipacionDao();
				listParticipacion = pd.cargarParticipacion();
				tablaParticipacion.setItems(listParticipacion);

				tablaParticipacion.setVisible(true);
				tablaEvento.setVisible(false);
			} else {
				ed = new EventoDao();
				listEventos = ed.cargarEvento();
				tablaEvento.setItems(listEventos);
				tablaParticipacion.setVisible(false);
				tablaEvento.setVisible(true);
			}
		}catch(SQLException e) {
			error("Error en el sql");
		}
		
	}

	// METODOS DE FILTRAR
	@FXML
	void filtrarTemporada(ActionEvent event) {
		
		try {
			listEventos = ed.cargarEvento();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("Error en el sql");
		}
		if (cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {

			try {
				listParticipacion = pd.FiltrarParticipacion(checkBoxInvierno.isSelected(), checkBoxVerano.isSelected());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				error("Error en el sql");
			}
			tablaParticipacion.setItems(listParticipacion);

		} else {
			ObservableList<Evento> listTemporada = FXCollections.observableArrayList();
			for (int i = 0; i < listEventos.size(); i++) {
				if (checkBoxInvierno.isSelected()) {
					if (listEventos.get(i).getOlTemporada().equals("Winter")) {
						listTemporada.add(listEventos.get(i));
					}
				}
				if (checkBoxVerano.isSelected()) {
					if (listEventos.get(i).getOlTemporada().equals("Summer")) {
						listTemporada.add(listEventos.get(i));
					}
				}
			}
			listEventos = listTemporada;
			tablaEvento.setItems(listEventos);
		}

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> arrTablas = FXCollections.observableArrayList();
		arrTablas.add("Evento");
		arrTablas.add("Participacion");
		cbTabla.setItems(arrTablas);
		cbTabla.getSelectionModel().select(0);
		tablaEvento.setVisible(true);
		// tablaParticipacion.setVisible(false);
		

		// ASIGNAR CAMPOS A LA TABLA EVENTO y los tamaños
		col1Posicion.setCellValueFactory(new PropertyValueFactory<>("nom_Evento"));
		col1Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.30));

		col2Posicion.setCellValueFactory(new PropertyValueFactory<>("OlNombre"));
		col2Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.20));

		col3Posicion.setCellValueFactory(new PropertyValueFactory<>("OlAnio"));
		col3Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.08));

		col4Posicion.setCellValueFactory(new PropertyValueFactory<>("OlTemporada"));
		col4Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.14));

		col5Posicion.setCellValueFactory(new PropertyValueFactory<>("OlCiudad"));
		col5Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.11));

		col6Posicion.setCellValueFactory(new PropertyValueFactory<>("DepNombre"));
		col6Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.17));

		// ASIGNAR CAMPOS A LA TABLA Participacion y los tamaños
		colNomDeportista.setCellValueFactory(new PropertyValueFactory<>("NomDeportista"));
		colNomDeportista.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.20));
		
		colSexo.setCellValueFactory(new PropertyValueFactory<>("SexoDeportista"));
		colSexo.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.08));
		
		colPeso.setCellValueFactory(new PropertyValueFactory<>("PesoDeportista"));
		colPeso.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.08));
		
		colAltura.setCellValueFactory(new PropertyValueFactory<>("AlturaDeportista"));
		colAltura.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.08));
		
		colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		colEdad.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.08));
		
		colEquipo.setCellValueFactory(new PropertyValueFactory<>("NombreEquipo"));
		colEquipo.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.15));
		
		colMedalla.setCellValueFactory(new PropertyValueFactory<>("Medalla"));
		colMedalla.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.15));
		
		colEvento.setCellValueFactory(new PropertyValueFactory<>("NomEvento"));
		colEvento.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.20));
		
		pd = new ParticipacionDao();
		// ASIGNAR A LA TABLA UNA LISTA
		ed = new EventoDao();
		try {
			listEventos = ed.cargarEvento();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("Error en el sql");
		}

//		
		tablaEvento.setItems(listEventos);
		
		dd=new DeportistaDao();
		//IMAGEN
		img.setImage(new Image(getClass().getResourceAsStream("/images/portada1.png"),300,100, false, false));
		
		//IDIOMAS
		String idioma = Propiedades.getValor("idioma");
		String region = Propiedades.getValor("region");
		Locale.setDefault(new Locale(idioma, region));
		bundle = ResourceBundle.getBundle("idiomas/messages");
		
		 
		 
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

}
