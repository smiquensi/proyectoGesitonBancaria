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

    private final String tituloTitularExiste = "Aviso de duplicado de titular";
    private final String headerTitularExiste = "Este titular ya se encuentra en la cuenta bancaria.";
    private final String contentTitularExiste = "Por favor, introduzca los datos de otra persona diferente.";

    private final String tituloTitularCorrecto = "Aviso de nuevo titular";
    private final String headerTitularCorrecto = "Este titular se ha añadido corectamente";
    private final String contentTitularCorrecto = "Por favor, introduzca los datos de otra persona para añadir mas titulares.";

    private final String tituloTitularNoElegido = "Aviso de titular no elegido";
    private final String headerTitularNoElegido = "No ha elegido ningún titular";
    private final String contentTitularNoElegido = "Por favor, elija un titular de la lista.";

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

    private final String tituloNumerosRojos = "Aviso!! Esta usted numeros rojos";
    private final String headerNumerosRojos = "Usted tiene un descubierto de ###";
    private final String contentNumerosRojos = "Por favor, regularice su situación cuanto antes.";

    
    private final String tituloBancarrota = "Aviso!! Esta usted bancarrota";
    private final String headerBancarrota = "Usted ha superado el descubierto máximo";
    private final String contentBancarrota = "Por favor, te aviso que te quito el piso";

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
            case 'T': // WARNING -> AVISO TITULAR YA EXISTENTE
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloTitularExiste);
                aviso.setHeaderText(headerTitularExiste);
                aviso.setContentText(contentTitularExiste);

                break;
            case 'L': // INFORMATIOM -> AVISO TITULAR ANYIADIDO CORRECTAMENTE
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(tituloTitularCorrecto);
                aviso.setHeaderText(headerTitularCorrecto);
                aviso.setContentText(contentTitularCorrecto);

                break;
            case 'R': // WARNING -> AVISO TITULAR ANYIADIDO CORRECTAMENTE
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloTitularNoElegido);
                aviso.setHeaderText(headerTitularNoElegido);
                aviso.setContentText(contentTitularNoElegido);

                break;
            case 'J': // WARNING -> AVISO NUMEROS ROJOS
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloNumerosRojos);
                aviso.setHeaderText(headerNumerosRojos);
                aviso.setContentText(contentNumerosRojos);

                break;
            case 'B': // WARNING -> AVISO ESTAS EN BANCARROTA
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(tituloBancarrota);
                aviso.setHeaderText(headerBancarrota);
                aviso.setContentText(contentBancarrota);
                
                break;

            default:

                throw new AssertionError();

        }

    }

}
