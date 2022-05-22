/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyecto.bancobase;

import auxiliar.Aviso;
import auxiliar.BancoBD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import modelo.Banco;
import modelo.CuentaBancaria;

/**
 * FXML Controller class
 *
 * @author Enrique
 * @author SantiMiquel

 */
public class PrimaryController implements Initializable {

    // Creamos instaciacion del objeto banco
    private static Banco banco = new Banco();

    List<CuentaBancaria> arrayCuentas = new ArrayList();

    ObservableList<CuentaBancaria> listadoClientes;

    // atributo estatico para compartir cuenta bancaria con el otro controlador.
    private static CuentaBancaria cuentaElegida;

    @FXML
    private ListView<CuentaBancaria> listadoCuentasBancarias;
    @FXML
    private Button selecionarCuenta;

    Aviso avisoCuentaNula = new Aviso('W');
    BancoBD bd = new BancoBD ();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarListView();
        
        try {
            System.out.println(bd.conectarBd());
        } catch (Exception ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // CARGAR CUENTAS EN EL LISTADO
    public void cargarListView() {
        
        Iterator<CuentaBancaria> it = banco.listaCuentasBancarias().iterator();
        while (it.hasNext()) {

            arrayCuentas.add(it.next());

        }
        
        listadoClientes = FXCollections.observableArrayList(arrayCuentas);
        listadoCuentasBancarias.setItems(listadoClientes);

    }

    // METODO QUE DEVUELVE UN INTEGER CON LA CUENTA SELECCIONADA
    public Integer cuentaSeleccionada() {

        Integer numeroEleccion = listadoCuentasBancarias.getSelectionModel().getSelectedIndex();
        CuentaBancaria cuentaSeleccionada = listadoClientes.get(numeroEleccion);
        cuentaElegida = cuentaSeleccionada;

        return numeroEleccion;

    }

    // METODO BOTON FXML PARA CARGAR LA CUENTA SELECIONADA EN EL SECONDARY
    @FXML
    private void cargarCuentaBancaria(ActionEvent event) throws IOException {

        try {
            cuentaSeleccionada();
            App.setRoot("secondary");
        } catch (RuntimeException e) {
           avisoCuentaNula.showAndWait();
        }

    }


    // METODO QUE DEVUELVE LA CUENTA BANCARIA ELEGIDA
    public static CuentaBancaria getCuentaElegida() {
        return cuentaElegida;

    }
}
