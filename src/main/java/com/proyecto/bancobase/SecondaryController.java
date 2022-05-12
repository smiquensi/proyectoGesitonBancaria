/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyecto.bancobase;

import auxiliar.Archivo;
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
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import modelo.CuentaBancaria;
import modelo.Movimiento;
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
    private CheckBox donacion;
    @FXML
    private CheckBox donacionIglesia;
    @FXML
    private CheckBox donacionSocial;
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

    //Movimientos//--------------------------------------------------------------------------------
    @FXML
    private TableView<Movimiento> tablaMovimientos;
    List<Movimiento> arrayListMovimientos = new ArrayList();
    ObservableList<Movimiento> listadoMovimientosObservableList;

    @FXML
    private TableColumn<Movimiento, LocalDateTime> columnaFecha;
    @FXML
    private TableColumn<Movimiento, String> columnaDni;
    @FXML
    private TableColumn<Movimiento, String> columnaImporte;
    @FXML
    private TableColumn<Movimiento, String> columnaMotivo;
    @FXML
    private TableColumn<Movimiento, String> columnaTipo;

    //--------------------------------------------------------------------------------  
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

    Archivo archivo = new Archivo();

    private static CuentaBancaria cuentaMostrada;
    private double dineroDonado;
    private static double dineroDonadoTotal;
    private final int MAXIMODONADO = 75;

    Aviso aviso = new Aviso('W');

    private double cantidadIngresada;
    private boolean isDonacionSelected;
    private boolean isCheckOutSelected;
    @FXML
    private ListView<Persona> listarTitulares;
    List<Persona> arrayTitulares = new ArrayList();
    ObservableList<Persona> listadoTitulares;
    int controlTitulares = -1;
    private static Persona titularElegido;
    @FXML
    private Label titularSeleccionadoLabel;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        cargarCuenta();
        cargarSpinnerIngreso();
        cargarSpinnerExtracto();
//        cargarMovimientos();

    }

    // METODO PARA CARGAR OBTENERCUENTA() EN EL OBSERVABLELIST
    public void cargarCuenta() {
        ObservableList<CuentaBancaria> resultadoCuenta = FXCollections.observableArrayList(obtenerCuenta());
        datosCuenta.setItems(resultadoCuenta);
        cargarTitulares();
        listarMovimientos();

    }

    public void cargarTitulares() {

        if (controlTitulares != cuentaMostrada.getTitulares().size()) {

            for (Persona temp : obtenerCuenta().getTitulares()) {

                if (!arrayTitulares.contains(temp)) {

                    arrayTitulares.add(temp);

                    listadoTitulares = FXCollections.observableArrayList(arrayTitulares);

                    listarTitulares.setItems(listadoTitulares);
                }
            }

            controlTitulares = cuentaMostrada.getTitulares().size();
        }
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

    @FXML
    private void titularSeleccionado(MouseEvent event) {

        int posSeleccionado = listarTitulares.getSelectionModel().getSelectedIndex();

        String cicloSeleccionado;
        cicloSeleccionado = arrayTitulares.get(posSeleccionado).getNombre() + " " + arrayTitulares.get(posSeleccionado).getNif();

        titularSeleccionadoLabel.setText(cicloSeleccionado);

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
        listarMovimientos();
        // CONTROL DE EXCEPCIONES PARA INPUTDINERO POR SI METEN TEXTO
        int tipoAvisoIngreso = -2; // REVISAR SI SE PUEDE INSTANCIAR SIN INICIALIZAR

        if (comprobarDatosIngreso()) {
            donacionTotal(calcularDonacion());

            tipoAvisoIngreso = cuentaMostrada.ingresar(nifIngreso.getText(), cantidadIngresada, conceptoIngreso.getText());
            conceptoDonacion();

            cargarCuenta();

            switch (tipoAvisoIngreso) {
                case -1: // CANTIDAD NEGATIVA 
                    lanzarAviso('D');

                    break;
                case 0: // INGRESO OK
                    lanzarAviso('C');
                    totalDonacionText.setText("Total donado: " + dineroDonadoTotal + "€");
                    cargarProgresoDonacion();

                    break;
                case 1: // AVISAR HACIENDA
                    lanzarAviso('H');
                    totalDonacionText.setText("Total donado: " + dineroDonadoTotal + "€");
                    cargarProgresoDonacion();

                    break;
            }
        }

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
    private void cargarDonacion(ActionEvent event) {
        double donativo = 0;
        String donacionString = "";

        if (donacionIglesia.isSelected() || donacionSocial.isSelected()) {

            if (donacionIglesia.isSelected()) {

                donativo = calcularDonacion();
                donacionString = String.valueOf(donativo);
                cantidadDonada.setText(donacionString + " €");
                cargarProgresoDonacion();
                totalDonacion.setProgress(cargarProgresoDonacion());

            }

            /*if (donacionSocial.isSelected()) {

                donativo = calcularDonacion();
                donacionString = String.valueOf(donativo);
                cantidadDonada.setText(donacionString + " €");
                cargarProgresoDonacion();
                totalDonacion.setProgress(cargarProgresoDonacion());

            }

            if (dineroDonadoTotal > 0) {
                cantidadDonada.setText(donacionString + " €");
                cargarProgresoDonacion();
                totalDonacion.setProgress(cargarProgresoDonacion());
            } else {
                totalDonacion.setProgress(0);
                totalDonacionText.setText(null);

            }*/
        }

    }

    private double calcularDonacion() {
        dineroDonado = cantidadIngreso.getValue() * 0.01;
        return dineroDonado;
    }

    // REVISAR ESTE METODO POR EL LIMITE DE 75 DEL ENUNCIADO
    private void donacionTotal(double donativo) {
        cantidadIngresada = cantidadIngreso.getValue();

        if (isDonacionSelected) {
            dineroDonadoTotal += donativo;
            cantidadIngresada -= donativo;
        }

    }

    private void conceptoDonacion() {
        if (donacionIglesia.isSelected()) {
            cuentaMostrada.ingresar(nifIngreso.getText(), dineroDonado, "Donación hecha a la iglesia");

        }
        if (donacionSocial.isSelected()) {
            cuentaMostrada.ingresar(nifIngreso.getText(), dineroDonado, "Donación hecha a organizacion social");
        }
    }

    private double cargarProgresoDonacion() {
        double reglaDeTresDonacion = ((100 * dineroDonadoTotal) / MAXIMODONADO) / 100;
        totalDonacion.setProgress(reglaDeTresDonacion);
        return reglaDeTresDonacion;
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
        archivo.importarArchivo();
//        String fileString = fichero.getName();
//        String fileString = fileString.substring(fileString.indexOf(".");
    }

    @FXML
    private void exportarMovimiento(ActionEvent event) {

        archivo.exportarArchivo(recolectarMovimiento());

    }

    public List<Movimiento> recolectarMovimiento() {
        char tipoMov = 'T';

        for (Movimiento emp : cuentaMostrada.listarMovimientos(tipoMov)) {
            if (!arrayListMovimientos.contains(emp)) {
                arrayListMovimientos.add(emp);
            }
        }

        return arrayListMovimientos;
    }

    private void listarMovimientos() { // Aqui filtraremos por char
        char tipoMov = 'T';
        tablaMovimientos.getItems().clear();
        tablaMovimientos.refresh();

        listadoMovimientosObservableList = FXCollections.observableArrayList();
        for (Movimiento emp : cuentaMostrada.listarMovimientos(tipoMov)) {
            if (!arrayListMovimientos.contains(emp)) {
                arrayListMovimientos.add(emp);
            }
        }
        listadoMovimientosObservableList = FXCollections.observableArrayList(arrayListMovimientos);
        tablaMovimientos.setItems(listadoMovimientosObservableList);

        columnaFecha.setCellValueFactory(new PropertyValueFactory<Movimiento, LocalDateTime>("fecha"));
        columnaDni.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("dni"));
        columnaImporte.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("cantidad"));
        columnaMotivo.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("motivo"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("tipo"));

    }

    private void lanzarAviso(char caracter) {
        aviso.cambioAviso(caracter);
        aviso.showAndWait();
    }

    // METODO PROVISONAL PARA PARTIR STRING -- METERLOS EN CONTROL
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
