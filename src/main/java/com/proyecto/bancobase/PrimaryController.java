/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyecto.bancobase;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Banco;
import modelo.CuentaBancaria;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Enrique
 */
public class PrimaryController implements Initializable {

    // Creamos instaciacion del objeto banco
    private Banco banco = new Banco();

    List<CuentaBancaria> arrayCuentas = new ArrayList();

    ObservableList<CuentaBancaria> listadoClientes;

    // atributo estatico para compartir cuenta bancaria con el otro controlador.
    private static CuentaBancaria cuentaElegida;

    @FXML
    private ListView<CuentaBancaria> listadoCuentasBancarias;
    @FXML
    private Button selecionarCuenta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarListView();
    }
// Creamos la iteracion de el hashset "cuentasBancarias.values()" y metemos cada propiedad "it.next().informacionCuenta()" en la listview.
    // despues 

    public void cargarListView() {

        Iterator<CuentaBancaria> it = banco.listaCuentasBancarias().iterator();
        while (it.hasNext()) {

            arrayCuentas.add(it.next());

        }

        listadoClientes = FXCollections.observableArrayList(arrayCuentas);
        listadoCuentasBancarias.setItems(listadoClientes);

    }

    public void cuentaSeleccionada() {

        int numeroEleccion = listadoCuentasBancarias.getSelectionModel().getSelectedIndex();
        CuentaBancaria cuentaSeleccionada = listadoClientes.get(numeroEleccion);
        cuentaElegida = cuentaSeleccionada;

    }

    public static CuentaBancaria getCuentaElegida() {
        return cuentaElegida;
    }

    @FXML
    private void cargarCuentaBancaria(ActionEvent event) throws IOException {
        cuentaSeleccionada();
        App.setRoot("secondary");
    }

}
