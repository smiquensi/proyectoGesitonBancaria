/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyecto.bancobase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Enrique
 */
public class SecondaryController implements Initializable {
 
 
    
    
     

    @FXML
    private Button volver;
    @FXML
    private Button botonAutorizarTitular;
    @FXML
    private Button botonDesautorizarTitular;
    @FXML
    private TextField nifImput;
    @FXML
    private TextField nombreImput;
    @FXML
    private Button ingresar;
    @FXML
    private Spinner<?> cantidadIngreso;
    @FXML
    private TextField nifIngreso;
    @FXML
    private TextField conceptoIngreso;
    @FXML
    private CheckBox donacion;
    @FXML
    private RadioButton donacionIglesia;
    @FXML
    private RadioButton donacionSocial;
    @FXML
    private Label cantidadDonada;
    @FXML
    private ProgressIndicator totalDonacion;
    @FXML
    private Label totalDonacionText;
    @FXML
    private Tab titularesTab;
    @FXML
    private Tab ingresarTab;
    @FXML
    private Tab extraerTab;
    @FXML
    private Tab movimientosTab;
    @FXML
    private Spinner<?> cantidadExtracto;
    @FXML
    private TextField nifExtracto;
    @FXML
    private TextField conceptoExtracto;
    @FXML
    private Button extraer;
    @FXML
    private TableView<?> tablaMovimientos;
    @FXML
    private RadioButton filtrarIngresos;
    @FXML
    private ToggleGroup filtrarMovimientos;
    @FXML
    private RadioButton filtrarExtractos;
    @FXML
    private DatePicker filtrarFecha;
    @FXML
    private Button importarMovimientos;
    @FXML
    private Button exportarMovimiento;
    @FXML
    private TextArea infoGeneral;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            

    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void autorizarTitular(ActionEvent event) {
    }

    @FXML
    private void desautorizarTitular(ActionEvent event) {
    }

    @FXML
    private void hacerIngreso(ActionEvent event) {
    }

    @FXML
    private void hacerDonacion(ActionEvent event) {
    }

    @FXML
    private void hacerExtracto(ActionEvent event) {
    }

    @FXML
    private void importarMovimientos(ActionEvent event) {
    }

    @FXML
    private void exportarMovimiento(ActionEvent event) {
    }

    
    public void displayInformacionGeneral (String informacion){
        infoGeneral.setText(informacion);
    }
    
}
