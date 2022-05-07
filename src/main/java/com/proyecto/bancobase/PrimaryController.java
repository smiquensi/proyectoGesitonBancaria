/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.proyecto.bancobase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML
    private ListView<String> listadoCuentasBancarias;
    @FXML
    private Button selecionarCuenta;
    @FXML
    private ImageView foto;

    @FXML
    private void cargarCuentaBancaria(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        crearListView();
    }
// Creamos la iteracion de el hashset "cuentasBancarias.values()" y metemos cada propiedad "it.next().informacionCuenta()" en la listview.
    // despues 
    public void crearListView() {

        Iterator<CuentaBancaria> it = banco.listaCuentasBancarias().iterator();
        while (it.hasNext()) {

            listadoCuentasBancarias.getItems().add(it.next().informacionCuenta() + " \n \n");

        }
        
        
        
    }

    


}
