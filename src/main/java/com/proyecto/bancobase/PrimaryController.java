/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyecto.bancobase;

import auxiliar.Aviso;
import auxiliar.BancoBD;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.css.StyleableProperty;
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

    public Integer cuentaSeleccionada() {

        Integer numeroEleccion = listadoCuentasBancarias.getSelectionModel().getSelectedIndex();
        CuentaBancaria cuentaSeleccionada = listadoClientes.get(numeroEleccion);
        cuentaElegida = cuentaSeleccionada;

        return numeroEleccion;

    }

    @FXML
    private void cargarCuentaBancaria(ActionEvent event) throws IOException {

        try {
            cuentaSeleccionada();
            App.setRoot("secondary");
        } catch (RuntimeException e) {
           avisoCuentaNula.showAndWait();
        }

    }


    public static CuentaBancaria getCuentaElegida() {
        return cuentaElegida;

    }
}
