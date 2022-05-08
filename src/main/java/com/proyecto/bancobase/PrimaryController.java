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
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
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

    ArrayList arrayListCuentasString = new ArrayList();
    ArrayList arrayListCuentas = new ArrayList();
    ObservableList<String> listaCuentaStrings;
    CuentaBancaria cuentaSeleccionada;
    String holimanoli = "sin modificar";
    ObservableSet<String> listadoClientes = FXCollections.emptyObservableSet();
    Parent root;

    @FXML
    private ListView<String> listadoCuentasBancarias;
    @FXML
    private Button selecionarCuenta;
    @FXML
    private Label seleccionado;

    @FXML
    private void cargarCuentaBancaria(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = loader.load();

            //The following both lines are the only addition we need to pass the arguments
            SecondaryController controller2 = loader.getController();
            controller2.displayInformacionGeneral(cambioCuenta());

            Stage stage = new Stage();
            stage = (Stage) selecionarCuenta.getScene().getWindow();
            stage.close();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // App.setRoot("secondary");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        listadoCuentasBancarias.setItems(crearListView());

    }
// Creamos la iteracion de el hashset "cuentasBancarias.values()" y metemos cada propiedad "it.next().informacionCuenta()" en la listview.
    // despues 

    public ObservableList<String> crearListView() {

        Iterator<CuentaBancaria> it = banco.listaCuentasBancarias().iterator();
        while (it.hasNext()) {

            //listadoCuentasBancarias.getItems().add(it.next().informacionCuenta() + " \n \n");
            arrayListCuentasString.add(it.next().informacionCuenta());
            // arrayListCuentas.add(it.next());

        }

        listaCuentaStrings = FXCollections.observableArrayList(arrayListCuentasString);

        return listaCuentaStrings;

    }

    private String cambioCuenta() {

        int posSeleccionada = listadoCuentasBancarias.getSelectionModel().getSelectedIndex();

        String cuentaSelecionada = crearListView().get(posSeleccionada);

        seleccionado.setText(cuentaSelecionada);

        //  Iterator<CuentaBancaria> it = banco.listaCuentasBancarias().iterator();
        //    while (it.hasNext()) {
        //      arrayListCuentas.add(it.next());
        //  }
        //cuentaSeleccionada = (CuentaBancaria) arrayListCuentas.get(posSeleccionada);
        holimanoli = seleccionado.getText();

        return holimanoli;
    }

}
