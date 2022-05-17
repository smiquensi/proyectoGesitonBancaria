/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import com.proyecto.bancobase.SecondaryController;
import java.awt.BorderLayout;
import javafx.scene.control.Alert;

/**
 *
 * @author santimiquel
 */
public class Aviso {

    private String title;
    private String headerText;
    private String contentText;
    private char tipoAviso;
    private double cantidadHacienda;

    Alert aviso = new Alert(Alert.AlertType.NONE);

    public double setCantidadHacienda(double cantidadHacienda) {

        this.cantidadHacienda = cantidadHacienda;
        return cantidadHacienda;
    }

    public double getCantidadHacienda() {
        return cantidadHacienda;
    }

    private final String tituloWarning = "Aviso de selección de cuenta";
    private final String headerTextWarning = "No se ha seleccionado ninguna cuenta.";
    private final String contentTextWarning = "Por favor, seleccione una cuenta.";

    private final String tituloInfo = "Aviso de numero de titulares";
    private final String headerTextInfo = "Ha llegado al maximo de titulares permitidos.";
    private final String contentTextInfo = "Por favor, para añadir más titulares, eliminar uno previamente.";
    private final String headerTextInfoB = "Ha llegado al minimo de titulares permitidos.";
    private final String contentTextInfoB = "Por favor, para eliminar más titulares, añada uno previamente.";

    private final String tituloImporte0 = "Aviso de importe igual a 0";
    private final String headerTextImporte0 = "Ha introducido un importe igual a 0";
    private final String contentTexImporte0 = "Por favor, introduzca un importe superior a 0";

    private final String tituloWarningTexto = "Aviso de introducción de texto";
    private final String headerTextWarningTexto = "No se ha introducido texto.";
    private final String contentTextWarningTexto = "Por favor, itroduzca texto para continuar.";

    private final String tituloConfirmacion = "Aviso de confirmación";
    private final String headerTextConfirmacion = "La operacion se ha realizado correctamente.";
    private final String contentTextConfirmacion = "Esta operación se ha añadido a su lista de movimientos";

    private final String tituloHacienda = "Información importante";
    private String headerTextHacienda = "Esta operación por importe de " + cantidadHacienda + "€ será reportada a hacienda";
    private final String contentTextHacienda = "Por motivos legales debemos informar de las operaciones superiores a 3000 €";

    private final String tituloWarningDonacion = "Aviso de máximo de donación";
    private final String headerTextWarningDonacion = "Enhorabuena, ha hecho una gran labor comunitaria";
    private final String contentTextWarningDonacion = "Usted ha llegado al máximo de donaciones permitdias.";

    private final String tituloNumberFormatException = "Aviso de formato incorrecto";
    private final String headerTextNumberFormatException = "No puede introducir texto en la selección de importe";
    private final String contentTextNumberFormatException = "Por favor, elija una cantidad cantidad correcta.";

    // FALTAN AVISO DE TITULAR AÑADIDO, DE TITULAR DUPLICADO, NO HAY DINERO SUFICIENTE
    // NUMEROS ROJOS
    public Aviso(String title, String headerText, String contentText) {
        this.title = title;
        this.headerText = headerText;
        this.contentText = contentText;
    }

    // METODO CONSTRUCTOR PARA AVISO CON IMPORTE DE HACIENDA *** REVISAR PARA MEJORAR***
    public Aviso(double cantidadHacienda) {
        this.cantidadHacienda = cantidadHacienda;
        this.headerText = "Esta operación por importe de " + cantidadHacienda + "€ será reportada a hacienda";

    }

    // METODO QUE LANZA EL MENSAJE DE HACIENDA  *** REVISAR PARA MEJORAR ***
    public void lanzarHacienda() {
        aviso = new Alert(Alert.AlertType.WARNING);
        aviso.setTitle(tituloHacienda);
        aviso.setHeaderText(headerText);
        aviso.setContentText(contentTextHacienda);
        aviso.showAndWait();
    }

    public Aviso(char tipoAviso) {
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
            case 'W': // WARNING -> DEBE ELEGIR UNA CUENTA PARA CONTINUAR
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloWarning);
                aviso.setHeaderText(headerText);
                aviso.setContentText(contentTextWarning);
                break;
            case 'V': // WARNING -> DEBE INTRODUCIR NIF Y NOMBRE
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloWarningTexto);
                aviso.setHeaderText(headerTextWarningTexto);
                aviso.setContentText(contentTextWarningTexto);
                break;
            case 'I':
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(tituloInfo);
                aviso.setHeaderText(headerTextInfo);
                aviso.setContentText(contentTextInfo);

                break;
            case 'C': // CONFIRMACIÓN -> SE HA REALIZADO OPERACION CORRECTAMENTE
                aviso.setAlertType(Alert.AlertType.CONFIRMATION);
                aviso.setTitle(tituloConfirmacion);
                aviso.setHeaderText(headerTextConfirmacion);
                aviso.setContentText(contentTextConfirmacion);

                break;
            case 'D': // WARNING -> DEBE INTRODUCIR UN IMPORTE SUPERIOR A 0
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloImporte0);
                aviso.setHeaderText(headerTextImporte0);
                aviso.setContentText(contentTexImporte0);

                break;
            case 'H': // INFO -> AVISO A HACIENDA
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloHacienda);
                aviso.setHeaderText(headerTextHacienda);
                aviso.setContentText(contentTextHacienda);

                break;
            case 'O': // WARNING -> AVISO NO HA ELEGIDO LA ENTIDAD DE DONACION
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloWarningDonacion);
                aviso.setHeaderText(headerTextWarningDonacion);
                aviso.setContentText(contentTextWarningDonacion);

                break;
            case 'N': // WARNING -> AVISO DE EXCEPCION NumberFormatException
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloNumberFormatException);
                aviso.setHeaderText(headerTextNumberFormatException);
                aviso.setContentText(contentTextNumberFormatException);

                break;
            case 'A': // WARNING -> AVISO DE EXCEPCION NumberFormatException
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(tituloWarningDonacion);
                aviso.setHeaderText(headerTextWarningDonacion);
                aviso.setContentText(contentTextWarningDonacion);

                break;

            default:

                throw new AssertionError();

        }

    }

}
