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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.*;
import auxiliar.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Enrique
 * @author SantiMiquel
 */
public class SecondaryController implements Initializable {

    @FXML
    private TextField datosCuenta;
    @FXML
    private Button volver;

    // ATRIBUTOS TITULARES 
    @FXML
    private Button botonAutorizarTitular;
    @FXML
    private Button botonDesautorizarTitular;
    @FXML
    private TextField nifInput;
    @FXML
    private TextField nombreInput;
    @FXML
    private ProgressBar numeroTitulares;
    @FXML
    private Label numeroTitularesText;

    // ATRIBUTOS INGRESAR 
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
    private Label iglesiaLabel;
    @FXML
    private Label socialLabel;

    // ATRIBUTOS TAB DEL TABPANE 
    @FXML
    private Tab titularesTab;
    @FXML
    private Tab ingresarTab;
    @FXML
    private Tab extraerTab;
    @FXML
    private Tab movimientosTab;

    // ATRIBUTOS EXTRACTO 
    @FXML
    private Spinner<Integer> cantidadExtracto;
    @FXML
    private TextField nifExtracto;
    @FXML
    private TextField conceptoExtracto;
    @FXML
    private Button extraer;

    // ATRIBUTOS MOVIMIENTOS 
    @FXML
    private TableView<Movimiento> tablaMovimientos;
    List<Movimiento> arrayListMovimientos = new ArrayList();
    ObservableList<Movimiento> listadoMovimientosObservableList;

    // ATRIBUTOS TABLA MOVIMIENTOS 
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
    private TableColumn<String, Integer> columnaNumeroMov;
    @FXML
    private TabPane panelTab;

    // ATRIBUTOS FILTRADO MOVIMIENTOS 
    @FXML
    private RadioButton filtrarIngresos;
    @FXML
    private RadioButton filtrarExtractos;
    @FXML
    private DatePicker filtrarFecha;
    @FXML
    private Button filtrarMovimientosButton;
    @FXML
    private Button filtrarMovimientosButton1;
    @FXML
    private ToggleGroup botonesFiltrado;

    // ATRIBUTOS IMPORTAR Y EXPORTAR DATOS A ARCHIVO 
    @FXML
    private Button importarMovimientos;
    @FXML
    private Button exportarMovimiento;
    @FXML
    private ListView<Persona> listarTitulares;

    // ATRIBUTOS IMPORTAR Y EXPORTAR DATOS A BD 
    @FXML
    private Button importarBD;
    @FXML
    private Button exportarBD;

    private static CuentaBancaria cuentaMostrada;
    private double dineroDonado;
    private static double dineroDonadoTotal;
    private static char salida;
    private static Persona titularElegido;

    private final int MAXIMODONADO = 75;
    private final double MAXIMOTITULARES = 5.;

    private double donativo;
    private String conceptoDonado;
    private Month mes;
    private int controlTitulares = -1;
    private Label titularSeleccionadoLabel;
    private CheckBox filtrarMovimientosCheck;
    private double cantidadIngresada;
    private boolean isDonacionSelected;
    private boolean isCheckOutSelected;
    private String saldo;

    Calendar calendar = Calendar.getInstance();
    BancoBD bd = new BancoBD();
    Archivo archivo = new Archivo();
    Aviso aviso = new Aviso('W');
    List<Persona> arrayMovimientosExportar = new ArrayList();
    List<Persona> arrayTitulares = new ArrayList();
    List<Persona> arrayTitularesDelete = new ArrayList();
    ObservableList<Persona> listadoTitulares;
    SpinnerValueFactory.IntegerSpinnerValueFactory dinero = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50000, 0, 1);

    /**
     * Initializes the controller class.
     */
    // METODO INITIALIZE PARA CARGAR INFORMACION DE LA CUENTA
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCuenta();
        cargarSpinnerIngreso();
        cargarSpinnerExtracto();
        try {
            bd.conectarBd();
        } catch (Exception ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            lanzarAviso('K');
        }

    }

    // METODO PARA ACTUALIZAR LAS MODIFICACIONES EN CUENTA BANCARIA 
    public void cargarCuenta() {
        ObservableList<CuentaBancaria> resultadoCuenta = FXCollections.observableArrayList(obtenerCuenta());
        saldo = cuentaMostrada.getSaldoFormateado();
        datosCuenta.setText("Nº Cuenta: " + cuentaMostrada.getNumCuenta() + " Saldo: " + saldo);
        cargarTitulares();
        cargarProgresoDonacion();
        listarMovimientos();
        desactivarDiasFuturos();
        cantidadTitulares();
        numerosRojos();

    }

    // METODO PARA CARGAR TITULARES 
    public void cargarTitulares() {

        if (cuentaMostrada.getTitulares().size() >= 0) {

            for (Persona titularPersona : cuentaMostrada.getTitulares()) {

                titularPersona.setNombre(titularPersona.getNombre().toUpperCase());

                if (!arrayTitulares.contains(titularPersona)) {

                    arrayTitulares.add(titularPersona);

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

    // METODO PARA VOLVER A LA PANTALLA INICIAL
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

    // METODO PARA BUSCAR SI EL NIF YA ESTA DE TITULAR Y AÑADIRLO EN CASO QUE EL NIF NO SE REPITA
    @FXML
    private void autorizarTitular(ActionEvent event) {
        if (anyadirTitulares()) {
            if (nifInput.getText() == null || nombreInput.getText() == null
                    || nifInput.getText().isEmpty() || nombreInput.getText().isEmpty()) { // ya funcionan por separado con ==null, pero hay que poner .isEmpty porque sino cargaria el primero vacio, al no iniciarse como null sino como empty

                lanzarAviso('V'); // WARNING -> DEBE INTRODUCIR NIF Y NOMBRE

            } else {
                if (!TextControl.formatoNif(nifInput.getText())) {
                    lanzarAviso('F');

                } else {
                    if (cuentaMostrada.nuevoTitular(nifInput.getText(), nombreInput.getText())) {
                        lanzarAviso('L'); // INFORMATIOM -> AVISO TITULAR ANYIADIDO CORRECTAMENTE

                    } else {
                        lanzarAviso('T'); // WARNING -> AVISO TITULAR YA EXISTENTE
                    }
                    limpiarCampos();
                }

            }

        }
        cargarCuenta();
    }

    // METODO QUE CARGA LA CANTIDADA DE TITULARES EN PROGRES
    private double cantidadTitulares() {
        double proporcionTitulares = cuentaMostrada.getTitulares().size() / MAXIMOTITULARES;
        numeroTitulares.setProgress(proporcionTitulares);
        numeroTitularesText.setText("Titulares en la cuenta -> " + cuentaMostrada.getTitulares().size() + "/5 permitidos");
        return proporcionTitulares;
    }

    // METODO PARA DESAUTORIZAR TITULARES
    @FXML
    private void desautorizarTitular(ActionEvent event) { // *** REVISAR PARA INTENTAR USAR METODOS DE CUENTA BANCARIA !!!!!
        //System.out.println(cuentaMostrada.getTitulares().size());
        try {

            if (cuentaMostrada.getTitulares().size() <= 1) {
                lanzarAviso('M');

            } else {

                if (aviso.getRespuesta()) {
                    cuentaMostrada.eliminaTitular(titularSeleccionado());

                    for (Persona temp : arrayTitulares) {

                        if (!cuentaMostrada.getTitulares().contains(temp)) {

                            arrayTitularesDelete.add(temp);
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
            }
        } catch (RuntimeException e) {

            lanzarAviso('R');

        } catch (AssertionError ex) {
            lanzarAviso('R');

        }
        cargarCuenta();
        limpiarCampos();

    }

    // METODO QUE DEVUELVE UN STRING CON EL TITULAR SELECCIONADO
    private String titularSeleccionado() {

        int posSeleccionado = listarTitulares.getSelectionModel().getSelectedIndex();

        String titularSeleccionadoDni = arrayTitulares.get(posSeleccionado).getNif();

        return titularSeleccionadoDni;
    }

    // METODO PARA COMPROBAR SI LOS DATOS INTRODUCIDOS SON CORRECTOS
    private boolean comprobarDatosIngreso() {
        boolean comprobarDatosIngreso = true;

        if (nifIngreso.getText().isEmpty() || conceptoIngreso.getText().isEmpty()) {
            comprobarDatosIngreso = false;
            lanzarAviso('V');

        } else {
            if (!TextControl.formatoNif(nifIngreso.getText())) {
                lanzarAviso('F');
                comprobarDatosIngreso = false;

            }
        }
        return comprobarDatosIngreso;
    }

    // METODO FXML PARA HACER EL INGRESO
    @FXML
    private void hacerIngreso(ActionEvent event) {
        int tipoAvisoIngreso = -2;

        if (comprobarDatosIngreso()) {
            if ((donacionSocial.isSelected() || donacionIglesia.isSelected()) && limiteDonacion()) {
                extraerDonacion();
            }
            cantidadIngresada = cantidadIngreso.getValue() - donativo;

            tipoAvisoIngreso = cuentaMostrada.ingresar(nifIngreso.getText(), cantidadIngresada, conceptoIngreso.getText());
            switch (tipoAvisoIngreso) {
                case -1: // CANTIDAD NEGATIVA 
                    lanzarAviso('D');

                    break;
                case 0: // INGRESO OK
                    if (aviso.goToMovimientos()) {
                        panelTab.getSelectionModel().select(movimientosTab);
                    }
                    totalDonacionText.setText("Total donado: " + cuentaMostrada.getDonaciones() + "€");
                    cargarProgresoDonacion();

                    break;
                case 1: // AVISAR HACIENDA
                    aviso = new Aviso(cantidadIngresada);
                    aviso.lanzarHacienda(); // lanzamos el aviso
                    totalDonacionText.setText("Total donado: " + cuentaMostrada.getDonaciones() + "€");
                    cargarProgresoDonacion();

                    break;
            }
            limpiarCamposIngreso();

        }
        listarMovimientos();
        cargarCuenta();
        limpiarDonacion();

    }

    // METODO QUE COMPRUEBA SI SE HA LLEGADO AL LIMITE DE DONACIONES
    private boolean limiteDonacion() {
        boolean seguir = false;
        if (cuentaMostrada.getDonaciones() <= MAXIMODONADO) {
            seguir = true;
        }
        return seguir;

    }

    // METODO QUE HACE EL EXTRACTO DE LA DONACION EN CUENTA BANCARIA
    private void extraerDonacion() {
        double donacionExtraccion = 0;
        String nifExtracion = nifIngreso.getText();
        String conceptoExtraccion = conceptoDonado;

        donacionExtraccion = donacionTotal(calcularDonacion());
        cuentaMostrada.sacar(nifExtracion, donacionExtraccion, conceptoExtraccion);
    }

    // METODO CALCULA EL PORCENTAGE DEL INGRESO QUE SE VA A DONAR
    private double calcularDonacion() {
        dineroDonado = cantidadIngreso.getValue() * 0.01;
        return dineroDonado;
    }

    // METODO QUE COMPRUEBA SI CON ESTA DONACION SE SOBREPASA EL LIMITE
    private double donacionTotal(double donativo) {
        if (cuentaMostrada.getDonaciones() + donativo > MAXIMODONADO) {
            donativo = MAXIMODONADO - cuentaMostrada.getDonaciones();
            lanzarAviso('A'); // aviso para maximo de donaciones
            donacionIglesia.setDisable(true);
            donacionSocial.setDisable(true);

        }
        cuentaMostrada.sumaDonacion(donativo);
        this.donativo = donativo;
        return donativo;
    }

    //  METODO AÑADE EL CONCEPTO PERSONALIZADO A LA DONACION
    @FXML
    private String cargarDonacion(ActionEvent event) {
        iglesiaLabel.setText("");
        socialLabel.setText("");

        if (donacionSocial.isSelected() || donacionIglesia.isSelected()) {
            cantidadDonada.setText("Cantidad donada: " + String.valueOf(calcularDonacion()) + " €");

            if (donacionIglesia.isSelected()) {
                conceptoDonado = "Donación hecha a la iglesia";
                iglesiaLabel.setText("-> " + String.format("%.2f", calcularDonacion()) + "€");
            }
            if (donacionSocial.isSelected()) {
                conceptoDonado = "Donación hecha a organizacion social";
                socialLabel.setText("-> " + String.format("%.2f", calcularDonacion()) + "€");

            }
            if (donacionSocial.isSelected() && donacionIglesia.isSelected()) {
                conceptoDonado = "Donación hecha a iglesia y  organizacion social";
                iglesiaLabel.setText("-> " + String.format("%.2f", calcularDonacion() / 2) + "€");
                socialLabel.setText("-> " + String.format("%.2f", calcularDonacion() / 2) + "€");

            }
        } else {
            cantidadDonada.setText("Cantidad donada");
        }
        return conceptoDonado;
    }

    //  METODO CALCULA UNA REGLA DE TRES PARA QUE LO MUESTRE EL PROGRESBAR
    private double cargarProgresoDonacion() {
        double reglaDeTresDonacion = ((100 * cuentaMostrada.getDonaciones()) / MAXIMODONADO) / 100;
        totalDonacion.setProgress(reglaDeTresDonacion);
        return reglaDeTresDonacion;
    }

    //  METODO COMPRUEBA DATOS NIF Y CONCEPOT EN OPERACION DE EXTRACCION
    private boolean comprobarDatosExtracto() {
        boolean comprobarDatosExtracto = true;

        if (nifExtracto.getText().isEmpty() || conceptoExtracto.getText().isEmpty()) {
            comprobarDatosExtracto = false;
            lanzarAviso('V');

        } else {
            if (!TextControl.formatoNif(nifExtracto.getText())) {
                lanzarAviso('F');
                comprobarDatosExtracto = false;

            }
        }
        return comprobarDatosExtracto;
    }

    // METODO FXML PARA HACER UN EXTRACTO
    @FXML
    private void hacerExtracto(ActionEvent event) {
        char tipoAvisoExtracto;
//        if (cuentaMostrada.esTitular(nifExtracto.getText())!= null) { // INTENTO DE HACER COMPROBACION DE 
        if (comprobarDatosExtracto()) {
            tipoAvisoExtracto = cuentaMostrada.sacar(nifExtracto.getText(), cantidadExtracto.getValue(), conceptoExtracto.getText());

            if (cantidadExtracto.getValue() <= 0) {
                tipoAvisoExtracto = '0';
            }

            switch (tipoAvisoExtracto) {
                case 'X': // WARNING -> AVISO ESTAS EN BANCARROTA
                    lanzarAviso('B');
                    break;
                case 'R': // WARNING -> AVISO NUMEROS ROJOS
                    aviso = new Aviso(cuentaMostrada.getSaldo()); // Creamos una nueva instanciacion de aviso y le pasamos la cantidad ingresada.
                    aviso.lanzarNumRojos(); // lanzamos el aviso

                    break;
                case 'V': // CONFIRMACIÓN -> SE HA REALIZADO OPERACION CORRECTAMENTE
                    lanzarAviso('C');
                    break;
                case '0': // WARNING -> DEBE INTRODUCIR UN IMPORTE SUPERIOR A 0 
                    lanzarAviso('D');
                    break;

            }

            limpiarCamposExtracto();

        }

        cargarCuenta();

    }

    // METODO QUE COMPRUEBA SI LA CUENTA ESTA EN NUMEROS ROJOS
    private void numerosRojos() {
        if (cuentaMostrada.getSaldo() < 0) {
            datosCuenta.setStyle("-fx-text-fill: #d4866e; -fx-background-color: #5c3935;");
        } else {
            datosCuenta.setStyle("");

        }
    }

    //METODO PARA IMPORTAR MOVIMIENTOS DESDE UN ARCHIVO
    @FXML
    private void importarMovimientos(ActionEvent event) throws ParseException {
        try {

            Iterator<Movimiento> it = TextControl.splitAlmohadilla(archivo.importarArchivo()).iterator();
            //mes = null;

            int lineas = 0;
            int i = 0;
            calendario();

            if (salida == 'T') {
                while (it.hasNext()) {

                    Movimiento tmp = it.next();
                    System.out.println(tmp);
                    if (!cuentaMostrada.listarMovimientos('T').contains(tmp)) {
                        if (tmp.getCantidad() != 0.0) {
                            cuentaMostrada.listarMovimientos('T').add(tmp);
                            lineas++;
                        }

                    }

                }
            } else if (salida == 'M') {

                calendar.clear();
                calendar.set(Calendar.MONTH, mes.getValue() - 1);
                calendar.set(Calendar.YEAR, LocalDate.now().getYear());
                Date date = calendar.getTime();
                LocalDateTime localDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

                while (it.hasNext()) {
                    Movimiento tmp = it.next();

                    if (!cuentaMostrada.listarMovimientos(localDate).equals(tmp)) {

                        if (tmp.getFecha().getMonth().equals(localDate.getMonth())) {
                            if (tmp.getCantidad() != 0.0) {
                                cuentaMostrada.listarMovimientos('T').add(tmp);
                                lineas++;
                            }
                        }

                    }

                }

            }
            aviso = new Aviso(lineas);
            aviso.lanzarAvisoImportacionOK();

        } catch (Exception e) {
            lanzarAviso('X');
        }

        cargarCuenta();
    }

    // METODO PARA EXPORTAR MOVIMIENTOS A UN ARCHIVO
    @FXML
    private void exportarMovimiento(ActionEvent event) {
        Set<Persona> tmp = cuentaMostrada.getTitulares();
        char tipoMovimiento;
        String tipoMovimientoString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaString;
        String nombrePersona = "";
        for (Persona tmpFor : tmp) {

            arrayMovimientosExportar.add(tmpFor);

        }
        nombrePersona = arrayMovimientosExportar.get(0).getNombre();
        if (filtrarExtractos.isSelected()) {
            tipoMovimiento = 'E';
            tipoMovimientoString = "Extractos";
        } else if (filtrarIngresos.isSelected()) {
            tipoMovimiento = 'I';
            tipoMovimientoString = "Ingresos";

        } else {
            tipoMovimiento = 'T';
            tipoMovimientoString = "General";

        }

        if (filtrarFecha.getValue() == null) {

            fechaString = LocalDate.now().format(formatter);
        } else {
            fechaString = filtrarFecha.getValue().format(formatter);
        }
        try {
            archivo.exportarArchivo(listarMovimientos(), nombrePersona, tipoMovimientoString, fechaString);

        } catch (Exception e) {

            lanzarAviso('X');
        }

    }

    // METODO PARA CARGAR LOS FILTROS DE LA DONACIONES
    @FXML
    private void cargarFiltrado(ActionEvent event) {
        listarMovimientos();
    }

    // METODO PARA CARGAR TODOS LOS MOVIMIENTOS
    @FXML
    private void cargarTodosMovimientos(ActionEvent event) {
        filtrarIngresos.setSelected(false);
        filtrarExtractos.setSelected(false);
        filtrarFecha.getEditor().clear();
        filtrarFecha.setValue(null);
        listarMovimientos();

    }

    // METODO PARA LISTAR MOVIMIENTOS POR TIPO 
    private List<Movimiento> listarMovimientos() {
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
            for (Movimiento movimiento : cuentaMostrada.listarMovimientos(LocalDateTime.of(filtrarFecha.getValue(), LocalTime.now()))) {
                if (!arrayListMovimientos.contains(movimiento)) {
                    if (filtrarIngresos.isSelected() || filtrarExtractos.isSelected()) {
                        if (movimiento.getTipo() == tipoMov) {

                            arrayListMovimientos.add(movimiento);

                        }
                    } else {
                        arrayListMovimientos.add(movimiento);
                    }
                }
            }

        } else {

            for (Movimiento tmp : cuentaMostrada.listarMovimientos(tipoMov)) {
                if (!arrayListMovimientos.contains(tmp)) {
                    arrayListMovimientos.add(tmp);
                }
            }
        }
        listadoMovimientosObservableList = FXCollections.observableArrayList(arrayListMovimientos);

        tablaMovimientos.setItems(listadoMovimientosObservableList);

        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaFecha.setCellFactory(tc -> new TableCell<Movimiento, LocalDateTime>() {
            @Override
            public void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText("");
                } else {
                    setText(item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")));
                }
            }
        });
        columnaDni.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("dni"));
        columnaImporte.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("cantidad"));
        columnaMotivo.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("motivo"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("tipo"));

        return arrayListMovimientos;
    }

    // METODO PARA LANZAR AVISOS
    private void lanzarAviso(char caracter) {
        aviso.cambioAviso(caracter);
        aviso.showAndWait();
    }

    // METODO PARA CARGAR LOS VALORES DEL SPINNER DE INGRESO
    public void cargarSpinnerIngreso() {
        cantidadIngreso.setValueFactory(dinero);
    }

    // METODO PARA CARGAR LOS VALORES DEL SPINNER DE EXTRACTO
    public void cargarSpinnerExtracto() {

        cantidadExtracto.setValueFactory(dinero);
    }

    // METODO PARA LIMPIAR LOS CAMPOS DE LA PESTAÑA TITULARES
    private void limpiarCampos() {
        nifInput.setText(null);
        nombreInput.setText(null);

    }

    // METODO PARA LIMPIAR LAS DONACIONES
    private void limpiarDonacion() {
        iglesiaLabel.setText(null);
        socialLabel.setText(null);

    }

    // METODO PARA LIMPIAR LOS CAMPOS DE LA PESTAÑA INGRESOS
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

    // METODO PARA LIMPIAR LOS CAMPOS DE LA PESTAÑA EXTRACTOS
    private void limpiarCamposExtracto() {
        dinero = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50000, 0, 1);
        cantidadExtracto.setValueFactory(dinero);
        nifExtracto.setText("");
        conceptoExtracto.setText("");
    }

    // METODO PARA DESACTIVAR DIA ACTUAL Y DIAS FUTUROS EN EL CALENDARIO DE MOVIMIENTOS
    public void desactivarDiasFuturos() {
        filtrarFecha.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) > 0);
            }
        });
    }

    // METODO SACA UN POPUP PARA SELECCIONAR EL MES EXPORTADO
    private void calendario() {
        Stage calendarioStage = new Stage();
        Month[] todosLosMeses = new Month[12];
        Label br = new Label();
        Label br1 = new Label();
        int mesInt;

        for (int i = 0;
                i < 13; i++) {
            if (i != 12) {
                todosLosMeses[i] = (Month.of(i + 1));
            }

        }
        ObservableList<Month> todosLosMesesObservableList = FXCollections.observableArrayList(todosLosMeses);

        ComboBox<Month> comboBox = new ComboBox(todosLosMesesObservableList);
        comboBox.getSelectionModel().selectFirst();

        VBox root = new VBox(comboBox);

        root.setAlignment(Pos.CENTER);

        root.getChildren()
                .add(br);

        Button btn1 = new Button();

        btn1.setText("Importar Mes");

        Button btn2 = new Button();

        btn2.setText("Importar Todo");

        root.getChildren().add(btn1);
        root.getChildren().add(br1);
        root.getChildren().add(btn2);

        Scene scene = new Scene(root, 250, 150);

        btn1.setOnAction(e -> {
            salida = 'M';
            mes = comboBox.getValue();
            calendarioStage.close();
        });

        btn2.setOnAction(e -> {
            salida = 'T';

            calendarioStage.close();
        });

        calendarioStage.setScene(scene);

        calendarioStage.showAndWait();

    }

    // METODO FXML PARA EXPORTAR MOVIMIENTOS EN LA BD
    @FXML
    private void exportarMovimientosBBDD(ActionEvent event) throws SQLException, Exception {
        try {
            bd.almacenarCuenta(cuentaMostrada);
            for (Movimiento tmp : listarMovimientos()) {

                bd.almacenarMovimiento(tmp);

            }
            lanzarAviso('Y');

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            lanzarAviso('Z');
        } catch (RuntimeException ex) {
            System.out.println(ex);
            lanzarAviso('K');
        }
    }

    // METODO FXML PARA IMPORTAR MOVIMIENTOS EN LA BD
    @FXML
    private void importarMovimientosBBDD(ActionEvent event) throws SQLException {

        for (Movimiento tmp : bd.listarMovimientos()) {

            arrayListMovimientos.add(tmp);

        }

        listadoMovimientosObservableList = FXCollections.observableArrayList(arrayListMovimientos);

        tablaMovimientos.setItems(listadoMovimientosObservableList);

        lanzarAviso('P');

    }

}
