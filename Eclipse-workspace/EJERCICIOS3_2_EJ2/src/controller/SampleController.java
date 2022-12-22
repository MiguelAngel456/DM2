package controller;

import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SampleController {
	@FXML
	private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ToggleGroup groupInformes;

    @FXML
    private RadioButton rbInformeCalculos;

    @FXML
    private RadioButton rbInformePersonas;

    @FXML
    private RadioButton rbSubInforme;

    @FXML
    void Aceptar(ActionEvent event) {
    	ConexionDB con;
		JasperReport report;
    	if(rbInformePersonas.isSelected()) {
    		try {
    			con = new ConexionDB();

				report = (JasperReport) JRLoader.loadObject(getClass().getResource("/informes/InformePersonas.jasper"));
					
				JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
		        JasperViewer viewer = new JasperViewer(jprint, false);
		        viewer.setTitle("Agenda");
		        viewer.setVisible(true);
		    } catch (Exception e) {
		    	System.out.println(e.getMessage());
		    }
    	}else {
    		if(rbInformeCalculos.isSelected()) {
    			try {
        			con = new ConexionDB();

    				report = (JasperReport) JRLoader.loadObject(getClass().getResource("/informes/InformePersonasCalculos.jasper"));
    					
    				JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
    		        JasperViewer viewer = new JasperViewer(jprint, false);
    		        viewer.setTitle("Agenda");
    		        viewer.setVisible(true);
    		    } catch (Exception e) {
    		    	System.out.println(e.getMessage());
    		    }
    		}
    	}
    }

    @FXML
    void Cancelar(ActionEvent event) {
    	
    }

	

}
