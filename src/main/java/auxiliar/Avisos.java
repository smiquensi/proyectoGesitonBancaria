/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import javafx.scene.control.Alert;

/**
 *
 * @author santimiquel
 */
public class Avisos {

    Alert aviso = new Alert(Alert.AlertType.NONE);
    private String title;
    private String headerText;
    private String contentText;
    private char tipoAviso;

    private final String tituloWarning = "Aviso de selección de cuenta";
    private final String headerTextWarning = "No se ha seleccionado ninguna cuenta.";
    private final String contentTextWarning = "Por favor, seleccione una cuenta.";

    private final String tituloInfo = "Aviso";
    private final String headerTextInfo = "Ha llegado al maximo de titulares permitidos.";
    private final String contentTextInfo = "Por favor, para añadir más titulares, eliminar uno previamente.";

    private final String tituloConfirmacion = "Aviso de selección de cuenta";
    private final String headerTextConfirmacion = "No se ha seleccionado ninguna cuenta.";
    private final String contentTextConfirmacion = "Por favor, seleccione una cuenta.";

    public Avisos(String title, String headerText, String contentText) {

        this.title = title;
        this.headerText = headerText;
        this.contentText = contentText;
    }

    public Avisos(char tipoAviso) {
        this.tipoAviso = tipoAviso;
        cambioAviso(tipoAviso);
    }

    public void showAndWait() {
        aviso.showAndWait();
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
    
    

    public void cambioAviso(char AlertType) {
        switch (AlertType) {
            case 'W':
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloWarning);
                aviso.setHeaderText(headerTextWarning);
                aviso.setContentText(contentTextWarning);
                break;
            case 'I':
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(tituloInfo);
                aviso.setHeaderText(headerTextInfo);
                aviso.setContentText(contentTextInfo);

                break;
            case 'C':
                aviso.setAlertType(Alert.AlertType.CONFIRMATION);
                aviso.setTitle(tituloConfirmacion);
                aviso.setHeaderText(headerTextConfirmacion);
                aviso.setContentText(contentTextConfirmacion);

                break;
            default:

                throw new AssertionError();

        }
        
    }

}
