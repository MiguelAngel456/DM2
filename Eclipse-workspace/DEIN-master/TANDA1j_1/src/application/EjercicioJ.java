package application;






import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EjercicioJ extends Application{
	private ImageView rojo;
	
	private ImageView azulClaro;
	
	private ImageView azulOscuro;
	
	private ImageView negro;
	
	private ImageView gris;
	
	private ImageView blanco;
	
	private ImageView grisOscuro;
	
	private ImageView amarillo;
	
	private ImageView luces;
	
	private ObservableList<ImageView> arrColores;
	private StackPane coche;
	private int turnoLuces;
	private ImageView[] arrImg;
	private VBox root;
	ImageView fond;
	public void start(Stage primaryStage) {
		//DECLARO EL LAYOUT
		root=new VBox();
		String[] colores={"BlazingRed","ElectricBlue","LapisluxuryBlue","MidnightBlack","MoonwalkGrey","PepperWhite","ThunderGray","VolcaninOrange"};
		arrImg=new ImageView[8];
		//DECLARAR LA LISTA
		
		//DECLARAR LOS BOTONES
			//boton luces
		String imagePath = getClass().getResource("/images/lucesOff.png").toString();
		Image im=new Image(imagePath);
		luces= new ImageView(im);
			//COLORES
		for (int i = 0; i < colores.length; i++) {
			imagePath = getClass().getResource("/images/color"+colores[i]+".jpg").toString();
			im=new Image(imagePath);
			arrImg[i]=new ImageView(im);
		}
			
				
		//DECLARAR LOS LABELS Y LA IMAGEN
		Label lblTitulo=new Label("CONFIGURA TU MINI COOPER");
		lblTitulo.setStyle("-fx-text-fill: white;");
		Label lblColores=new Label("Selecciona tu color favorito");
		String logo = getClass().getResource("/images/cooperLogo.png").toString();
		Image imgLogo=new Image(logo);
		
		//AÑADIR A LA PESTAÑA
			//TOP
		FlowPane fp=new FlowPane(40,40);
		fp.getChildren().add(new ImageView(imgLogo));
		fp.getChildren().add(lblTitulo);
		fp.setAlignment(Pos.CENTER);
		root.getChildren().add(fp);
			//LEFT IZQUIERDA
		StackPane.setAlignment(luces, Pos.TOP_LEFT);
		String fondo = getClass().getResource("/images/miniBlazingRed.png").toString();
		Image imfondo=new Image(fondo);
		
		fond=new ImageView(imfondo);
		StackPane.setAlignment(fond, Pos.CENTER); 
		
		coche = new StackPane(fond,luces);
		root.getChildren().add(coche);
		
		
		
		
		
		
		
			//BOT ABAJO
		GridPane gp=new GridPane();
		Label lblColor=new Label("Selecciona la luz preferida");
		lblColor.setStyle("-fx-text-fill: white;");
		gp.add(lblColor, 0,0);
		gp.setAlignment(Pos.CENTER);
		FlowPane fp2=new FlowPane(20,20);
		for (int i = 0; i < arrImg.length; i++) {
			fp2.getChildren().add(arrImg[i]);
		}
		fp2.setAlignment(Pos.CENTER);
		gp.add(fp2, 0, 1);
		
		ColumnConstraints c1=new ColumnConstraints();
		 c1.setHgrow(Priority.ALWAYS);
		 gp.getColumnConstraints().add(c1);
		root.getChildren().add(gp);
		
		root.setPrefSize(600,400);
		 //EVENTOS
			luces.setOnMouseClicked(e -> EncenderApagarluces());
		for (int i = 0; i < arrImg.length; i++) {
			int num=i;
			arrImg[i].setOnMouseClicked(e->cambiarColor(colores[num]));
		}
		
		
		 String simb = getClass().getResource("/images/Cooper.png").toString();
		 primaryStage.getIcons().add(new Image(simb));
//		 

		 Scene scene = new Scene(root);
		 
		// scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
//		 primaryStage.setResizable(false);
//         primaryStage.setMaximized(false);
		 primaryStage.setScene(scene);
		 primaryStage.setTitle("COOPER");
		 primaryStage.show();
		 primaryStage.sizeToScene();
		
	}
//	
	public void EncenderApagarluces() {
		if(turnoLuces==0) {
			String imagePath = getClass().getResource("/images/lucesOn.png").toString();
			Image im=new Image(imagePath);
			luces.setImage(im);
			turnoLuces++;
			String luz = getClass().getResource("/images/autoLuz.png").toString();
			Image imluz=new Image(luz);
			FlowPane fp=new FlowPane();
				ImageView im2=new ImageView(imluz);

			coche.getChildren().add(im2);
			
		}else {
			String imagePath = getClass().getResource("/images/lucesOff.png").toString();
			Image im=new Image(imagePath);
			luces.setImage(im);
			turnoLuces--;
			coche.getChildren().remove(2);
		}
	}
	public void cambiarColor(String color) {
		System.out.println("mini"+color+".png");
		//root.getChildren().remove(coche);
		
		String fondo = getClass().getResource("/images/mini"+color+".png").toString();
		Image imfondo=new Image(fondo);
		fond=new ImageView(imfondo);
		
		coche.getChildren().set(1,fond);
		
//		StackPane.setAlignment(fond, Pos.CENTER); 
//		
//		String imagePath = getClass().getResource("/images/lucesOff.png").toString();
//		Image im=new Image(imagePath);
//		luces= new ImageView(im);
//		StackPane.setAlignment(luces, Pos.TOP_LEFT);
////		
//		coche = new StackPane(luces);
//		root.getChildren().set(turnoLuces, fond1);
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
