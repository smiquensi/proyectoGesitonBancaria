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
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modelo.CuentaBancaria;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author Enrique
 */
public class SecondaryController implements Initializable {

    @FXML
    private ListView<CuentaBancaria> datosCuenta;
    @FXML
    private Button volver;
    @FXML
    private Button botonAutorizarTitular;
    @FXML
    private Button botonDesautorizarTitular;

    @FXML
    private TextField nifInput;
    @FXML
    private TextField nombreInput;

    @FXML
    private Button ingresar;
    @FXML
    private Spinner<Integer> cantidadIngreso;
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
    private Spinner<Integer> cantidadExtracto;
    @FXML
    private TextField nifExtracto;
    @FXML
    private TextField conceptoExtracto;
    @FXML
    private Button extraer;
    @FXML
    private TableView<String> tablaMovimientos;
    @FXML
    private RadioButton filtrarIngresos;
    @FXML
    private ToggleGroup filtrarMovimientos;
    @FXML
    private ToggleGroup grupoDonacion;
    @FXML
    private RadioButton filtrarExtractos;
    @FXML
    private DatePicker filtrarFecha;
    @FXML
    private Button importarMovimientos;
    @FXML
    private Button exportarMovimiento;

    private CuentaBancaria cuentaMostrada;
    private double dineroDonado;
    private static double dineroDonadoTotal;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCuenta();
        cargarSpinnerIngreso();
        cargarSpinnerExtracto();
        cargarMovimientos();
    }

    // METODO PARA CARGAR OBTENERCUENTA() EN EL OBSERVABLELIST
    public void cargarCuenta() {
        ObservableList<CuentaBancaria> resultadoCuenta = FXCollections.observableArrayList(obtenerCuenta());
        datosCuenta.setItems(resultadoCuenta);
    }

    // METODO PARA OBTENER LA CUENTA SELECCIONADA DEL PRIMARY CONTROLLER
    public CuentaBancaria obtenerCuenta() {
        cuentaMostrada = PrimaryController.getCuentaElegida();
        return cuentaMostrada;
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    // METODO PARA SABER SI HAY MENOS DE 5 TITULARES
    public boolean anyadirTitulares() {
        boolean masTitulares = false;
        if (cuentaMostrada.getTitulares().size() < 5) {
            masTitulares = true;
        }
        return masTitulares;
    }

    // METODO PARA BUSCAR SI EL NIF YA ESTA DE TITULAR
    @FXML
    private void autorizarTitular(ActionEvent event) {
        if (anyadirTitulares()) {
            cuentaMostrada.nuevoTitular(nifInput.getText(), nombreInput.getText());
        }
        // RELLAMAMOS AL METODO CARGAR CUENTA PARA ACTUALICE LA INFO DE LA VENTANA
        // ESTATICA (LA VENTANA DE ARRIBA)
        cargarCuenta();
    }

    @FXML
    private void desautorizarTitular(ActionEvent event) {
        cuentaMostrada.eliminaTitular(nifInput.getText());
        cargarCuenta();
    }

    @FXML
    private int hacerIngreso(ActionEvent event) {
        // EL METODO INGRESAR DE CUENTA BANCARIA DEVUELVO UN ENTERO QUE NECEISTAMOS
        // PARA SABER QUE TIPO DE ALERTA MOSTRAR
        int tipoAvisoIngreso = cuentaMostrada.ingresar(nifIngreso.getText(), cantidadIngreso.getValue(), conceptoIngreso.getText());
        cargarCuenta();
        return tipoAvisoIngreso;
    }

    @FXML
    private double hacerDonacion(ActionEvent event) {
        double donativo = 0;
        if (donacion.isSelected()) {
            donativo = calcularDonacion();
            donacionIglesia.setDisable(false);
            donacionSocial.setDisable(false);
            String donacionString = String.valueOf(donativo);
            cantidadDonada.setText(donacionString + " €");
            donacionTotal(donativo);
        } else {
            donativo = 0;
            donacionIglesia.setDisable(true);
            donacionSocial.setDisable(true);
            cantidadDonada.setText(null);
        }

        return donativo;

    }

    private double calcularDonacion() {
        dineroDonado = cantidadIngreso.getValue() * 0.01;
        return dineroDonado;
    }
    
    // REVISAR ESTE METODO POR EL LIMITE DE 75 DEL ENUNCIADO
    private void donacionTotal(double donativo){
        dineroDonadoTotal += donativo; 
        totalDonacion = new ProgressIndicator(); // NO FUNCIONA
        totalDonacion.setProgress(dineroDonadoTotal); // NO FUNCIONA
    }

    @FXML
    private char hacerExtracto(ActionEvent event) {
        char tipoAvisoExtracto = cuentaMostrada.sacar(nifExtracto.getText(), cantidadExtracto.getValue(), conceptoExtracto.getText());
        cargarCuenta();
        return tipoAvisoExtracto;
    }
    
    @FXML
    private void importarMovimientos(ActionEvent event) {
    }

    @FXML
    private void exportarMovimiento(ActionEvent event) {
    }
    
    // METODO PARA CARGAR LA TABLELIST CON LOS MOVIMIENTOS. !!INVESTIGAR¡¡
    private void cargarMovimientos(){
        
    }

    public void cargarSpinnerIngreso() {
        SpinnerValueFactory.IntegerSpinnerValueFactory dinero = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50000, 0, 1);
        cantidadIngreso.setValueFactory(dinero);
    }

    public void cargarSpinnerExtracto() {
        SpinnerValueFactory.IntegerSpinnerValueFactory dinero = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50000, 0, 1);
        cantidadExtracto.setValueFactory(dinero);
    }

}
