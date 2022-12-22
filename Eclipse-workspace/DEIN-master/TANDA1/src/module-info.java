module TANDA1 {
	requires javafx.controls;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens ejerciciob to javafx.graphics, javafx.fxml;
	opens ejercicioC to javafx.graphics, javafx.fxml;
	opens ejercicioD to javafx.graphics, javafx.fxml;
	opens ejercicioE to javafx.graphics, javafx.fxml;
	opens ejercicioF to javafx.graphics, javafx.fxml;
	opens model to javafx.graphics, javafx.fxml, javafx.base;
}
