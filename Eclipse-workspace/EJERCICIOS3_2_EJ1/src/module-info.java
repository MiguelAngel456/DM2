module EJERCICIOS3_2 {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.web;
	requires javafx.fxml;
	requires javafx.swing;
	requires javafx.media;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires org.kordamp.ikonli.javafx;
	requires jasperreports;

	
	opens application to javafx.graphics, javafx.fxml;
}
