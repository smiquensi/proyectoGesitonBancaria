/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyecto.bancobase;

import auxiliar.Archivo;
import auxiliar.Aviso;
import auxiliar.TextControl;
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
import modelo.*;
import auxiliar.*;
import java.time.LocalDate;
import javafx.scene.control.DateCell;

/**
 * FXML Controller class
 *
 * @author Enrique
 */
public class SecondaryController implements Initializable {

    @FXML
    private TextField datosCuenta;
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
    List<Persona> arrayMovimientosExportar = new ArrayList();

    private double cantidadIngresada;
    private boolean isDonacionSelected;
    private boolean isCheckOutSelected;
    @FXML
    private ListView<Persona> listarTitulares;
    List<Persona> arrayTitulares = new ArrayList();
    List<Persona> arrayTitularesDelete = new ArrayList();
    ObservableList<Persona> listadoTitulares;
    int controlTitulares = -1;
    private static Persona titularElegido;
    private Label titularSeleccionadoLabel;
    private CheckBox filtrarMovimientosCheck;
    SpinnerValueFactory.IntegerSpinnerValueFactory dinero = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50000, 0, 1);

    @FXML
    private Button filtrarMovimientosButton;
    @FXML
    private ToggleGroup grupoFiltradoMovimientos;
    @FXML
    private Button filtrarMovimientosButton1;
    @FXML
    private Label iglesiaLabel;
    @FXML
    private Label socialLabel;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        cargarCuenta();
        cargarSpinnerIngreso();
        cargarSpinnerExtracto();

    }

    // METODO PARA CARGAR OBTENERCUENTA() EN EL OBSERVABLELIST
    public void cargarCuenta() {
        ObservableList<CuentaBancaria> resultadoCuenta = FXCollections.observableArrayList(obtenerCuenta());
        datosCuenta.setText("Nº Cuenta: " + cuentaMostrada.getNumCuenta() + " Saldo: " + cuentaMostrada.getSaldoFormateado());
        cargarTitulares();
        listarMovimientos();
        desactivarDiasFuturos();

    }

    public void cargarTitulares() {

        if (controlTitulares != cuentaMostrada.getTitulares().size()) {

            for (Persona temp : obtenerCuenta().getTitulares()) {

                temp.setNombre(temp.getNombre().toUpperCase());

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

    // METODO PARA SABER SI HAY MENOS DE 5 TITULARES
    public boolean anyadirTitulares() {
        boolean masTitulares;
        if (cuentaMostrada.getTitulares().size() < 5) {
            masTitulares = true;
        } else {
            nombreInput.setDisable(true); // desactiva la entrada de datos cuando hay mas de 5 titulares
            nifInput.setDisable(true);  //PROBLEMA. como estos textfield son compartidos por desautorizar titular no se puede acceder a este metodo
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
        //System.out.println(cuentaMostrada.getTitulares().size());
        if (cuentaMostrada.getTitulares().size() > 1) {
            if (titularSeleccionado() == null) {
                lanzarAviso('V');

            } else {
                cuentaMostrada.eliminaTitular(titularSeleccionado());

                for (Persona temp : arrayTitulares) {

                    if (!cuentaMostrada.getTitulares().contains(temp)) {

                        arrayTitularesDelete.add(temp);
//                        listarTitulares.getSelectionModel().clearSelection();
//                        listadoTitulares = FXCollections.observableArrayList(arrayTitulares);
//                        listarTitulares.setItems(listadoTitulares);
                    }

                }

                for (Persona temp : arrayTitularesDelete) {

                    arrayTitulares.remove(temp);

                }
                listarTitulares.getSelectionModel().clearSelection();
                listadoTitulares = FXCollections.observableArrayList(arrayTitulares);
                listarTitulares.setItems(listadoTitulares);

                if (cuentaMostrada.getTitulares().size() <= 4) {
                    nombreInput.setDisable(false);
                    nifInput.setDisable(false);
                }

            }
        } else {
            lanzarAviso('W');
        }
        cargarCuenta();
        limpiarCampos();

    }

    private String titularSeleccionado() {

        int posSeleccionado = listarTitulares.getSelectionModel().getSelectedIndex();

        String titularSeleccionadoDni = arrayTitulares.get(posSeleccionado).getNif();

        return titularSeleccionadoDni;
    }

    @FXML
    private void hacerIngreso(ActionEvent event) {

        // CONTROL DE EXCEPCIONES PARA INPUTDINERO POR SI METEN TEXTO
        int tipoAvisoIngreso = -2; // REVISAR SI SE PUEDE INSTANCIAR SIN INICIALIZAR

        if (comprobarDatosIngreso()) {

            // donacionTotal(calcularDonacion());
            if ((donacionSocial.isSelected() || donacionIglesia.isSelected()) && limiteDonacion()) {

                extraerDonacion();

            }
            cantidadIngresada = cantidadIngreso.getValue();

            tipoAvisoIngreso = cuentaMostrada.ingresar(nifIngreso.getText(), cantidadIngresada, conceptoIngreso.getText());

//            cargarCuenta();   
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
                    aviso.setCantidadHacienda(99999999999.0);
                    totalDonacionText.setText("Total donado: " + dineroDonadoTotal + "€");
                    cargarProgresoDonacion();

                    break;
            }

        }
//        extraerDonacion();
        listarMovimientos();
        limpiarCamposIngreso();
        cargarCuenta();
        limpiarDonacion();

    }

    private boolean limiteDonacion() {
        boolean seguir = false;

        if (dineroDonadoTotal <= 75) {

            seguir = true;

        }

        return seguir;

    }

    private void extraerDonacion() {
        double donacionExtraccion = 0;
        if (donacionSocial.isSelected() || donacionIglesia.isSelected()) {

            String nifExtracion = nifIngreso.getText();
            donacionExtraccion = donacionTotal(calcularDonacion());
            if (donacionExtraccion >= 75) {

                donacionExtraccion = 75 - dineroDonadoTotal;

            }
            String conceptoExtraccion = conceptoDonacion();
            cuentaMostrada.sacar(nifExtracion, donacionExtraccion, conceptoExtraccion);
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
        iglesiaLabel.setText("");
        socialLabel.setText("");

        if (donacionSocial.isSelected() || donacionIglesia.isSelected()) {
            cantidadDonada.setText("Cantidad donada: " + String.valueOf(calcularDonacion()) + " €");

            if (donacionIglesia.isSelected()) {
                iglesiaLabel.setText("-> " + calcularDonacion() + "€");
            }
            if (donacionSocial.isSelected()) {
                socialLabel.setText("-> " + calcularDonacion() + "€");

            }
            if (donacionSocial.isSelected() && donacionIglesia.isSelected()) {
                iglesiaLabel.setText("-> " + calcularDonacion() / 2 + "€");
                socialLabel.setText("-> " + calcularDonacion() / 2 + "€");

            }
        } else {
            cantidadDonada.setText("Cantidad donada");
        }

//        double donativo = 0;
//        String donacionString = "";
    }

    // METODO CALCULA EL PORCENTAGE DEL INGRESO QUE SE VA A DONAR
    private double calcularDonacion() {
        dineroDonado = cantidadIngreso.getValue() * 0.01;
        return dineroDonado;
    }

    // REVISAR ESTE METODO POR EL LIMITE DE 75 DEL ENUNCIADO
    private double donacionTotal(double donativo) {
        try { // NO HACE NADA
            cantidadIngresada = cantidadIngreso.getValue();
            if (donacionSocial.isSelected() || donacionIglesia.isSelected() && limiteDonacion()) {
                dineroDonadoTotal += donativo;
                //cantidadIngresada -= donativo;
            } else {
                dineroDonadoTotal = 75;
            }

        } catch (NumberFormatException e) {
            System.out.println("Has metido letras en vez de numeros. donacionTotal()");
        }
        return dineroDonado;
    }

    //  METODO AÑADE EL CONCEPTO PERSONALIZADO A LA DONACION
    private String conceptoDonacion() {
        String conceptoDonado = "";
        if (donacionIglesia.isSelected()) {
            conceptoDonado = "Donación hecha a la iglesia";
            iglesiaLabel.setText("-> " + calcularDonacion() + "€");
        }
        if (donacionSocial.isSelected()) {
            conceptoDonado = "Donación hecha a organizacion social";
            socialLabel.setText("-> " + calcularDonacion() + "€");

        }
        if (donacionSocial.isSelected() && donacionIglesia.isSelected()) {
            conceptoDonado = "Donación hecha a iglesia y  organizacion social";
            iglesiaLabel.setText("-> " + calcularDonacion() / 2 + "€");
            socialLabel.setText("-> " + calcularDonacion() / 2 + "€");

        }
        return conceptoDonado;
    }

    //  METODO CALCULA UNA REGLA DE TRES PARA QUE LO MUESTRE EL PROGRESBAR
    private double cargarProgresoDonacion() {
        double reglaDeTresDonacion = ((100 * dineroDonadoTotal) / MAXIMODONADO) / 100;
        totalDonacion.setProgress(reglaDeTresDonacion);
        return reglaDeTresDonacion;
    }

    //  METODO COMPRUEBA DATOS NIF Y CONCEPOT EN OPERACION DE EXTRACCION
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

        Iterator<Movimiento> it = TextControl.splitAlmohadilla(archivo.importarArchivo()).iterator();
        while (it.hasNext()) {
            Movimiento tmp = it.next();
            cuentaMostrada.listarMovimientos('T').add(tmp);
        }
        cargarCuenta();

    }

    @FXML
    private void exportarMovimiento(ActionEvent event) {
        Set<Persona> tmp = cuentaMostrada.getTitulares();
        String nombrePersona = "";
        for (Persona tmpFor : tmp) {

            arrayMovimientosExportar.add(tmpFor);

        }
        nombrePersona = arrayMovimientosExportar.get(0).getNombre();
        archivo.exportarArchivo(recolectarMovimiento(), nombrePersona);

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

    @FXML
    private void cargarFiltrado(ActionEvent event) {
        listarMovimientos();
    }

    @FXML
    private void cargarTodosMovimientos(ActionEvent event) {
        filtrarIngresos.setSelected(false);
        filtrarExtractos.setSelected(false);
        filtrarFecha.getEditor().clear();
        filtrarFecha.setValue(null);
        listarMovimientos();

    }

    private void listarMovimientos() { // Aqui filtraremos por char
        char tipoMov = 'T';
        if (filtrarIngresos.isSelected()) {
            tipoMov = 'I';

        }
        if (filtrarExtractos.isSelected()) {
            tipoMov = 'E';

        }

        listadoMovimientosObservableList = FXCollections.observableArrayList();
        arrayListMovimientos.removeAll(arrayListMovimientos);
        tablaMovimientos.getItems().clear();
        tablaMovimientos.refresh();
        if (filtrarFecha.getValue() != null) {
            for (Movimiento emp : cuentaMostrada.listarMovimientos(filtrarFecha.getValue().atStartOfDay())) {
                if (!arrayListMovimientos.contains(emp) && emp.getTipo() == tipoMov) {

                    arrayListMovimientos.add(emp);
                }
            }

        } else {

            for (Movimiento emp : cuentaMostrada.listarMovimientos(tipoMov)) {
                if (!arrayListMovimientos.contains(emp)) {
                    arrayListMovimientos.add(emp);
                }
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
        cantidadIngreso.setValueFactory(dinero);
    }

    public void cargarSpinnerExtracto() {

        cantidadExtracto.setValueFactory(dinero);
    }

    private void limpiarCampos() {
        nifInput.setText(null);
        nombreInput.setText(null);

    }

    private void limpiarDonacion() {
        iglesiaLabel.setText(null);
        socialLabel.setText(null);

    }

    private void limpiarCamposIngreso() {
        dinero = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50000, 0, 1);
        cantidadIngreso.setValueFactory(dinero);
        nifIngreso.setText("");
        conceptoIngreso.setText("");
        donacionIglesia.setSelected(false);
        donacionSocial.setSelected(false);
        cantidadDonada.setText(null);
        iglesiaLabel.setText("");
        socialLabel.setText("");

    }

    public void desactivarDiasFuturos() {
        filtrarFecha.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) > 0);
            }
        });
    }
}
