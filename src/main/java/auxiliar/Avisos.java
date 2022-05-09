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

    private final String tituloWarning = "CASA";
    private final String headerTextWarning = "COSA";
    private final String contentTextWarning = "CUSA";

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

                break;
            case 'C':
                aviso.setAlertType(Alert.AlertType.CONFIRMATION);

                break;
            default:

                throw new AssertionError();

        }
        
    }

}
