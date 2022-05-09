/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyecto.bancobase;

import auxiliar.Aviso;
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
    private final int MAXIMODONADO = 75;
    @FXML
    private TableColumn<String, String> columnaFecha;
    @FXML
    private TableColumn<String, String> columnaDni;
    @FXML
    private TableColumn<?, ?> columnaImporte;
    @FXML
    private TableColumn<?, ?> columnaMotivo;
    @FXML
    private TableColumn<?, ?> columnaTipo;

    Aviso aviso = new Aviso('W');

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
         //totalDonacion.setProgress(0.25F);
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
        boolean masTitulares;
        if (cuentaMostrada.getTitulares().size() < 5) {
            masTitulares = true;
        } else {
            nombreInput.setDisable(true); // desactivar la entrada de datos cuando hay mas de 5 titulares
            // nifInput.setDisable(true);  //PROBLEMA. como estos textfield son compartidos por desautorizar titular no se puede acceder a este metodo
            lanzarAviso('I');

            masTitulares = false;

        }
        return masTitulares;
    }

    // METODO PARA BUSCAR SI EL NIF YA ESTA DE TITULAR
    @FXML
    private void autorizarTitular(ActionEvent event) {
        System.out.println(cuentaMostrada.getTitulares().size());
        if (anyadirTitulares()) {
            if (nifInput.getText() == null || nombreInput.getText() == null
                    || nifInput.getText().isEmpty() || nombreInput.getText().isEmpty()) { // ya funcionan por separado con ==null, pero hay que poner .isEmpty porque sino cargaria el primero vacio, al no iniciarse como null sino como empty

                lanzarAviso('V');

            } else {
                if (cuentaMostrada.nuevoTitular(nifInput.getText(), nombreInput.getText())) {
                    // Devuelve true, el titular se ha añadido correctamente. 
                    // mostarlo por aviso o por label
                } else {
                    // El titular esta duplicado, lanzar mensaje de aviso
                }
            }
        }
        // RELLAMAMOS AL METODO CARGAR CUENTA PARA ACTUALICE LA INFO DE LA VENTANA
        // ESTATICA (LA VENTANA DE ARRIBA)
        cargarCuenta();
        limpiarCampos();
    }

    @FXML
    private void desautorizarTitular(ActionEvent event) {
        System.out.println(cuentaMostrada.getTitulares().size());
        if (cuentaMostrada.getTitulares().size() > 1) {
            if (nifInput.getText().isEmpty()) {
                lanzarAviso('V');

            } else {
                cuentaMostrada.eliminaTitular(nifInput.getText());
                if (cuentaMostrada.getTitulares().size() == 4) {
                    nombreInput.setDisable(false);
                }
            }
        } else {
            lanzarAviso('W');
        }
        limpiarCampos();
        cargarCuenta();
    }

    @FXML
    private void hacerIngreso(ActionEvent event) {
        // EL METODO INGRESAR DE CUENTA BANCARIA DEVUELVO UN ENTERO QUE NECEISTAMOS
        // PARA SABER QUE TIPO DE ALERTA MOSTRAR

        int tipoAvisoIngreso = -2; // REVISAR SI SE PUEDE INSTANCIAR SIN INICIALIZAR
        if (comprobarDatosIngreso()) {
            tipoAvisoIngreso = cuentaMostrada.ingresar(nifIngreso.getText(), cantidadIngreso.getValue(), conceptoIngreso.getText());
            cargarCuenta();
        }
        switch (tipoAvisoIngreso) {
            case -1: // CANTIDAD NEGATIVA 
                lanzarAviso('D');

                break;
            case 0: // INGRESO OK
                lanzarAviso('C');

                break;
            case 1: // AVISAR HACIENDA
                lanzarAviso('H');
                break;

        }

//        return tipoAvisoIngreso;
    }

    private boolean comprobarDatosIngreso() {
        boolean comprobarDatosIngreso = true;

        if (nifIngreso.getText().isEmpty() || conceptoIngreso.getText().isEmpty()) {
            comprobarDatosIngreso = false;
            lanzarAviso('V');

        }
        return comprobarDatosIngreso;
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
    private void donacionTotal(double donativo) {
        dineroDonadoTotal += donativo;
     
        double reglaDeTresDonacion = ((100 *dineroDonadoTotal)/MAXIMODONADO)/100;
        System.out.println(reglaDeTresDonacion);
        totalDonacion.setProgress(reglaDeTresDonacion); 
        totalDonacionText.setText(totalDonacionText.getText() + " " + dineroDonadoTotal + "€");
        
        cargarCuenta();
    }

    private boolean comprobarDatosExtracto() {
        boolean comprobarDatosExtracto = true;

        if (nifExtracto.getText().isEmpty() || conceptoExtracto.getText().isEmpty()) {
            comprobarDatosExtracto = false;
            lanzarAviso('V');

        }
        return comprobarDatosExtracto;
    }

    @FXML
    private void hacerExtracto(ActionEvent event) {
        if (comprobarDatosExtracto()) {
            char tipoAvisoExtracto = cuentaMostrada.sacar(nifExtracto.getText(), cantidadExtracto.getValue(), conceptoExtracto.getText());
            switch (tipoAvisoExtracto) {
                case 'X': // no hay dinero suficiente - FALTA el aviso
                    lanzarAviso('W');
                    break;
                case 'R': // numeros rojos - FALTA el aviso
                    lanzarAviso('W');
                    break;
                case 'V': // extraccion ok
                    lanzarAviso('C');
                    break;
                case '0': // importe igual a 0 - CREO QUE ESTE NO FUNCIONA BIEN 
                    lanzarAviso('D');
                    break;

            }
        } else {
//            lanzarAviso('W');
        }
        cargarCuenta();
//        return tipoAvisoExtracto;
    }

    @FXML
    private void importarMovimientos(ActionEvent event) {

    }

    @FXML
    private void exportarMovimiento(ActionEvent event) {
    }

    // METODO PARA CARGAR LA TABLELIST CON LOS MOVIMIENTOS
    // A LA ESPERA DE Q RAQUEL DEJE CARGAR OBJETOS DE LA CLASE MOVIMIENTO
    private void cargarMovimientos() {

        columnaDni.setCellValueFactory(c -> new SimpleStringProperty(new String(cuentaMostrada.listarMovimientos('T'))));

        tablaMovimientos.getItems().addAll("Column one's data");
    }

    private void lanzarAviso(char caracter) {
        aviso.cambioAviso(caracter);
        aviso.showAndWait();
    }

    // METODO PROVISONAL PARA PARTIR STRING 
    private void splitString() {
        String[] lineas = cuentaMostrada.listarMovimientos('T').split("\\r?\\n");

        for (int i = 0; i < lineas.length; i++) {

            String[] splited = lineas[i].split("\\s+");
            for (int j = 0; j < splited.length; j++) {

            }

        }
    }

    public void cargarSpinnerIngreso() {
        SpinnerValueFactory.IntegerSpinnerValueFactory dinero = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50000, 0, 1);
        cantidadIngreso.setValueFactory(dinero);
    }

    public void cargarSpinnerExtracto() {
        SpinnerValueFactory.IntegerSpinnerValueFactory dinero = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50000, 0, 1);
        cantidadExtracto.setValueFactory(dinero);
    }

    private void limpiarCampos() {
        nifInput.setText(null);
        nombreInput.setText(null);

    }

}
