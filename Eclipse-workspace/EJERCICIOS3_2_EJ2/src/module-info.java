module EJERCICIOS3_2_EJ2 {
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
		opens controller to javafx.graphics, javafx.fxml;
	}
