package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.stream.FileImageInputStream;

import dao.ProductosDao;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Productos;

public class PrincipalController implements Initializable{
  @FXML
    private TableColumn<?, ?> ColumNombre;

    @FXML
    private Button btnActualizar;

    @FXML
    private MenuItem AcercaDe;
    @FXML
    private Button btnCrear;

    @FXML
    private Button btnImagen;

    @FXML
    private Button btnLimpiar;

    @FXML
    private CheckBox cbDisponible;

    @FXML
    private MenuItem cmEliminar;

    @FXML
    private TableColumn<?, ?> columCodigo;
    @FXML
    private ImageView imgProd;

    @FXML
    private TableColumn<?, ?> columDisponible;

    @FXML
    private TableColumn<?, ?> columPrecio;

    @FXML
    private TableView<Productos> tablaProductos;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;
    @FXML
    private GridPane gp;

    @FXML
    private TextField txtPrecio;

    private ObservableList<Productos> listaProductos;
    private ProductosDao pd;
    private Productos proAnt;
    private File archivo;
    
    @FXML
    void Actualizar(ActionEvent event) {
    	Window w= btnCrear.getScene().getWindow();
    	if(comprobar(2).length()==0) {
			String codigo=txtCodigo.getText();
    		String nom=txtNombre.getText();
    		float precio=Float.parseFloat(txtPrecio.getText());
    		int disponible;
    		if(cbDisponible.isSelected()) {
    			disponible=1;
    		}else {
    			disponible=0;
    		}
    		InputStream foto=null;
    		Productos p=new Productos(codigo, nom, precio, disponible, foto);
			try {
				pd.modificarProducto(p);
				info(w, "Producto cambiado correctamente");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
			error(w,2);
		}
		tablaProductos.setItems(pd.cargarProductos());
		tablaProductos.refresh();
    }

    @FXML
    void crear(ActionEvent event) {
    	Window w= btnCrear.getScene().getWindow();
    	if(comprobar(1).length()==0) {
    		String codigo=txtCodigo.getText();
    		String nom=txtNombre.getText();
    		float precio=Float.parseFloat(txtPrecio.getText());
    		int disponible;
    		if(cbDisponible.isSelected()) {
    			disponible=1;
    		}else {
    			disponible=0;
    		}
    		InputStream foto=null;
    		Productos p=new Productos(codigo, nom, precio, disponible, foto);
    		pd.aniadirProducto(p);
    		info(w, "Producto añadido correctamente");
    	}else {
    		
    		error(w,1);
    	}

		tablaProductos.setItems(pd.cargarProductos());
		tablaProductos.refresh();
    }

    @FXML
    void limpiar(ActionEvent event) {
    	txtCodigo.setText("");
    	txtCodigo.setDisable(false);
    	txtNombre.setText("");
    	txtPrecio.setText("");
    	tablaProductos.setSelectionModel(null);
    	this.btnActualizar.setDisable(true);
    	this.btnCrear.setDisable(false);
    	this.cbDisponible.setSelected(false);
    	Window w= btnCrear.getScene().getWindow();
    	info(w, "Campos limpiados correctamente");
    }
    @FXML
	void selectImg(ActionEvent event) {
		FileChooser FC=new FileChooser();
		FC.setTitle("Elige la imagen");
		FC.setInitialDirectory(new File("/home/dm2/Escritorio"));
		FC.getExtensionFilters().add(new ExtensionFilter("Archivo imagen", "*.png", "*.jpeg"));
		FC.setSelectedExtensionFilter(FC.getExtensionFilters().get(0));
		archivo=FC.showOpenDialog((Stage)btnImagen.getScene().getWindow());
		//ENSEÑAR LA IMAGEN
		try {
			this.imgProd.setImage(new Image(new FileInputStream(archivo)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    @FXML
    void Eliminar(ActionEvent event) {
    	Productos p=tablaProductos.getSelectionModel().getSelectedItem();
    	pd.eliminarProducto(p);
    	Window w= btnCrear.getScene().getWindow();
    	info(w, "Producto eliminado correctamente");
    	tablaProductos.setItems(pd.cargarProductos());
		tablaProductos.refresh();
    }

    @FXML
    Productos seleccionar() {
    	this.btnCrear.setDisable(true);
    	this.btnActualizar.setDisable(false);
    	Productos p=tablaProductos.getSelectionModel().getSelectedItem();
    	txtCodigo.setText(p.getCodigo());
    	txtCodigo.setDisable(true);
    	txtNombre.setText(p.getNombre());
    	txtPrecio.setText(String.valueOf(p.getPrecio()));
    	if(p.getDisponible()==1) {
    		cbDisponible.setSelected(true);
    	}else {
    		cbDisponible.setSelected(false);
    	}
    	proAnt=tablaProductos.getSelectionModel().getSelectedItem();
    	return p;
    }
    @FXML
    void acercaDe(ActionEvent event) {
    	Window w= btnCrear.getScene().getWindow();
    	infoPropia(w);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		pd=new ProductosDao();
		btnActualizar.setDisable(true);
		
		listaProductos=pd.cargarProductos();
		columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columCodigo.prefWidthProperty().bind(tablaProductos.widthProperty().multiply(0.25));
		ColumNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		ColumNombre.prefWidthProperty().bind(tablaProductos.widthProperty().multiply(0.25));
		columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columPrecio.prefWidthProperty().bind(tablaProductos.widthProperty().multiply(0.25));
		columPrecio.setStyle("-fx-alignment: CENTER-RIGHT;");
		columDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
		columDisponible.prefWidthProperty().bind(tablaProductos.widthProperty().multiply(0.25));
		columDisponible.setStyle("-fx-alignment: CENTER;");
		
		
//			columDisponible.setCellValueFactory(cellData -> {
//	            Productos p = (Productos) cellData.getValue();
//	            Boolean v = (p.getDisponibleCategory() == Productos.DisponibleCategory.Si);
//	            return new ReadOnlyBooleanWrapper(v);
//	        });;
//		columDisponible.setCellFactory(CheckBoxTableCell.<Productos>forTableColumn(null));
		
		tablaProductos.setItems(listaProductos);
	}
	public String comprobar(int tipo) {
		String fallos="";
		String codigo=txtCodigo.getText();
		String nom=txtNombre.getText();
		float precio=Float.parseFloat(txtPrecio.getText());
		int disponible;
		if(cbDisponible.isSelected()) {
			disponible=1;
		}else {
			disponible=0;
		}
		InputStream foto=null;
		Productos p=new Productos(codigo, nom, precio, disponible, foto);
		if(pd.cargarProductos().contains(p) && tipo==1) {
			fallos+="\n Ya existe ese producto";
		}
		if(txtCodigo.getText().length()!=5) {
			fallos+="\n El codigo tiene que ser 5 caracteres";
		}
		if(txtNombre.getText().length()==0) {
			fallos+="\n El campo nombre es obligatorio";
		}
		if(txtPrecio.getText().length()==0) {
			fallos+="\n El campo precio es obligatorio";
		}
		try {
			float i=Float.parseFloat(txtPrecio.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			fallos+="\n El campo precio tiene que contener solo numeros";
		}
		return fallos;
	}
	public void error (Window win,int tipo) {
		Alert alert;
		String texto=comprobar(tipo);
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(texto);
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("ERROR");
		alert.showAndWait();
	}
	public void info(Window win,String mens) {
		Alert alert;
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(mens);
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("CONFIRMACION");
		alert.showAndWait();
	}
	public void infoPropia(Window win) {
		Alert alert;
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("Gestion de productos 1.0\nAutor: Miguel Angel Fuente");
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("CONFIRMACION");
		alert.showAndWait();
	}

}

