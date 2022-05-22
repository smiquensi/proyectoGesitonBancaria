/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import com.proyecto.bancobase.SecondaryController;
import java.awt.BorderLayout;
import java.util.Optional;
import javafx.beans.property.ReadOnlyStringPropertyBase;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

/**
 *
 * @author santimiquel
 * @author Enrique
 */
public class Aviso {

    private String title;
    private String headerText;
    private String contentText;
    private char tipoAviso;
    private double cantidad;
    private ButtonType irMovimientos;
    private DialogPane dialog;

     private final String TITULOWARNING = "Aviso de selección de cuenta";
    private final String HEADERTEXTWARNING = "No se ha seleccionado ninguna cuenta.";
    private final String CONTENTTEXTWARNING = "Por favor, seleccione una cuenta.";

    private final String TITULOINFO = "Aviso de numero de titulares";
    private final String HEADERTEXTINFO = "Ha llegado al maximo de titulares permitidos.";
    private final String CONTENTTEXTINFO = "Por favor, para añadir más titulares, eliminar uno previamente.";
    private final String HEADERTEXTINFOB = "Ha llegado al minimo de titulares permitidos.";
    private final String CONTENTETEXTINFOB = "Por favor, para eliminar más titulares, añada uno previamente.";
    private final String HEADERTEXTINFOC = "¿Está seguro de eliminar al titular";
    private final String CONTENTETEXTINFOC = "Por favor, pulse \'Aceptar\' para confirmar";

    private final String TITULOTITULAREXISTENTE = "Aviso de duplicado de titular";
    private final String HEADERTITULAREXISTENE = "Este titular ya se encuentra en la cuenta bancaria.";
    private final String CONTENTTITULAREXISTENTE = "Por favor, introduzca los datos de otra persona diferente.";

    private final String TITULOTITULARCORRECTO = "Aviso de nuevo titular";
    private final String HEADERTITULARCORRECTO = "Este titular se ha añadido corectamente";
    private final String CONTENTTITULARCORRECTO = "Por favor, introduzca los datos de otra persona para añadir mas titulares.";

    private final String TITULOTITULARNOELEGIDO = "Aviso de titular no elegido";
    private final String HEADERTITULARNOELEGIDO = "No ha elegido ningún titular";
    private final String CONTENTTITULARNOELEGIDO = "Por favor, elija un titular de la lista.";

    private final String TITULOIMPORTE0 = "Aviso de importe igual a 0";
    private final String HEADERTITULOIMPORTE0 = "Ha introducido un importe igual a 0";
    private final String CONTENTTITULOIMPORTE0 = "Por favor, introduzca un importe superior a 0";

    private final String TITULOWARNINGTEXTO = "Aviso de introducción de texto";
    private final String HEADESWARNINGTEXTO = "No se ha introducido texto.";
    private final String CONTENTWARNINGTEXTO = "Por favor, itroduzca texto para continuar.";

    private final String TITULOWARNINGNIF = "Aviso de NIF incorrecto";
    private final String HEADERWARNINGNIF = "El formato de su NIF no es correcto.";
    private final String CONTENTWARNINGNIF = "Por favor, itroduzca correctamente su NIF.";

    private final String TITULOCONFIRMACION = "Aviso de confirmación";
    private final String HEADERCONFIRMACION = "La operacion se ha realizado correctamente.";
    private final String CONTENTCONFIRMACION = "Esta operación se ha añadido a su lista de movimientos";

    private final String TITULOHACIENDA = "Información importante";
    private String headerTextHacienda = "Esta operación por importe de " + cantidad + "€ será reportada a hacienda";
    private final String CONTENTHACIENDA = "Por motivos legales debemos informar de las operaciones superiores a 3000 €";

    private final String TITULOWARNINGDONACION = "Aviso de máximo de donación";
    private final String HEADERWARNINGDONACION = "Enhorabuena, ha hecho una gran labor comunitaria";
    private final String CONTENTWARNINGDONACION = "Usted ha llegado al máximo de donaciones permitdias.";

    private final String TITULONUMEROSROJOS = "Aviso!! Esta usted numeros rojos";
    private final String HEADERNUMEROSROJOS = "Usted tiene un descubierto de ###";
    private final String CONTENTNUMEROSROJOS = "Por favor, regularice su situación cuanto antes.";

    private final String TITULOBANCARROTA = "Aviso!! Esta usted bancarrota";
    private final String HEADERBANCARROTA = "Usted ha superado el descubierto máximo";
    private final String CONTENTBANCARROTA = "Por favor, te aviso que te quito el piso";

    private final String TITULOIMPORTMOV = "Información sobre la importación";
    private final String HEADERIMPORTMOV = "Ha llegado al maximo de titulares permitidos.";
    private final String CONTENTIMPORTMOV = "Para añadir más movimientos pulse el botón importar";

    private final String TITULONUMBEREXCEPTION = "Aviso de formato incorrecto";
    private final String HEADERNUMBEREXCEPTION = "No puede introducir texto en la selección de importe";
    private final String CONTENTNUMBEREXCEPTION = "Por favor, elija una cantidad cantidad correcta.";

    private final String TITULOFALLOINTEGRIDAD = "Aviso de fallo integridad";
    private final String HEADERFALLOINTEGRIDAD = "No puede añadir estos campos a la base de datos";
    private final String CONTENTFALLOINTEGRIDAD = "Estos campos ya han sido añadidos anteriormente.";

    private final String TITULOIMPORTADOCORRECTOBD = "Informacion de la Base de datos";
    private final String CONTENTIMPORTADOCORRECTOBD = "La informacion se ha importado correctamente.";

    private final String TITULOEXPORTADOCORRECTOBD = "Informacion de la Base de datos";
    private final String CONTENTEXPORTADOCORRECTOBD = "La informacion se ha exportado correctamente.";

    private final String TITULODIRECTORIONULO = "Fallo en el directorio";
    private final String CONTENTDIRECTORIONULO = "No se ha elegido directorio.";

    private final String TITULOCONEXIONFALLIDABD = "Aviso de BD";
    private final String CONTENTCONEXIONFALLIDABD = "No se ha podido establecer la conexion con la base de datos.";

    Alert aviso = new Alert(Alert.AlertType.NONE);

     // METODO CONSTRUCTOR PARA AVISO
    public Aviso(String title, String headerText, String contentText) {
        this.title = title;
        this.headerText = headerText;
        this.contentText = contentText;
    }

    // METODO CONSTRUCTOR PARA AVISO CON IMPORTE
    public Aviso(double cantidad) {
        this.cantidad = cantidad;

    }

    // METODO SETTER PARA PASAR EL IMPORTE DE NOTIFICACION A HACIENDA
    public double setCantidad(double cantidadHacienda) {

        this.cantidad = cantidadHacienda;
        return cantidadHacienda;
    }

    // METODO GETTER QUE DEVUELVE LA CANTIDAD
    public double getCantidad() {
        return cantidad;
    }

    // METODO QUE LANZA EL MENSAJE DE HACIENDA 
    public void lanzarHacienda() {
        this.headerText = "Esta operación por importe de " + cantidad + "€ será reportada a hacienda";
        aviso = new Alert(Alert.AlertType.WARNING);
        aviso.setTitle(TITULOHACIENDA);
        aviso.setHeaderText(headerText);
        aviso.setContentText(CONTENTHACIENDA);
        aviso.showAndWait();
    }

    // METODO QUE LANZA EL MENSAJE DE NUMEROS ROJOS 
    public void lanzarNumRojos() {
        this.headerText = "Usted tiene un descubierto de " + String.format("%.2f", cantidad) + " €";
        aviso = new Alert(Alert.AlertType.WARNING);
        aviso.setTitle(TITULONUMEROSROJOS);
        aviso.setHeaderText(headerText);
        aviso.setContentText(CONTENTNUMEROSROJOS);
        aviso.showAndWait();
    }

    // METODO QUE LANZA EL MENSAJE IMPORTACIÓN OK
    public void lanzarAvisoImportacionOK() {

        this.headerText = "Ha añadido " + String.format("%.0f", cantidad) + " movimientos nuevos.";
        aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle(TITULOIMPORTMOV);
        aviso.setHeaderText(headerText);
        aviso.setContentText(CONTENTIMPORTMOV);
        aviso.showAndWait();
    }

    // METODO PARA IR A LA PESTAÑA MOVIMIENTOS
    public boolean goToMovimientos() {
        cambioAviso('C');
        boolean respuesta = false;
        Optional<ButtonType> resultado = aviso.showAndWait();

        if (resultado.get() == irMovimientos) {
            respuesta = true;
        }
        return respuesta;
    }

    // METODO QUE LANZA MENSAJE DE TITULAR ELIMINADO
    public boolean getRespuesta() {
        cambioAviso('Q');
        boolean respuesta = false;
        Optional<ButtonType> resultado = aviso.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            respuesta = true;
        }
        return respuesta;
    }

    // METODO QUE CAMBIA EL TIPO DE AVIOS EN EL SWITCH
    public Aviso(char tipoAviso) {
        this.tipoAviso = tipoAviso;
        cambioAviso(tipoAviso);
    }

    // METODO QUE LANZA EL MENSAJE
    public void showAndWait() {
        aviso.showAndWait();
    }

    // METODO SETTER QUE AÑADE CONTENIDO
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
    
    // METODO SWITCH QUE RECIBE UN CHAR Y CAMBIA EL TIPO DE AVISO
    public void cambioAviso(char AlertType) {

        switch (AlertType) {
              case 'W': // WARNING -> DEBE ELEGIR UNA CUENTA PARA CONTINUAR
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOWARNING);
                aviso.setHeaderText(headerText);
                aviso.setContentText(CONTENTTEXTWARNING);
                break;
            case 'V': // WARNING -> DEBE INTRODUCIR NIF Y NOMBRE
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOWARNINGTEXTO);
                aviso.setHeaderText(HEADESWARNINGTEXTO);
                aviso.setContentText(CONTENTWARNINGTEXTO);
                break;
            case 'I': // INFO -> Aviso de numero de titulares
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(TITULOINFO);
                aviso.setHeaderText(HEADERTEXTINFO);
                aviso.setContentText(CONTENTTEXTINFO);

                break;
            case 'C': // CONFIRMACIÓN -> SE HA REALIZADO OPERACION CORRECTAMENTE
                aviso.setAlertType(Alert.AlertType.CONFIRMATION);
                irMovimientos = new ButtonType("Ir a movimientos");
                aviso.setTitle(TITULOCONFIRMACION);
                aviso.setHeaderText(HEADERCONFIRMACION);
                aviso.setContentText(CONTENTCONFIRMACION);
                aviso.getButtonTypes().setAll(ButtonType.OK, irMovimientos);

                break;
            case 'D': // WARNING -> DEBE INTRODUCIR UN IMPORTE SUPERIOR A 0
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOIMPORTE0);
                aviso.setHeaderText(HEADERTITULOIMPORTE0);
                aviso.setContentText(CONTENTTITULOIMPORTE0);

                break;
            case 'H': // INFO -> AVISO A HACIENDA
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOHACIENDA);
                aviso.setHeaderText(headerTextHacienda);
                aviso.setContentText(CONTENTHACIENDA);

                break;
            case 'O': // WARNING -> AVISO NO HA ELEGIDO LA ENTIDAD DE DONACION
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOWARNINGDONACION);
                aviso.setHeaderText(HEADERWARNINGDONACION);
                aviso.setContentText(CONTENTWARNINGDONACION);

                break;
            case 'N': // WARNING -> AVISO DE EXCEPCION NumberFormatException
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULONUMBEREXCEPTION);
                aviso.setHeaderText(HEADERNUMBEREXCEPTION);
                aviso.setContentText(CONTENTNUMBEREXCEPTION);

                break;
            case 'A': // WARNING -> AVISO DE EXCEPCION NumberFormatException
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(TITULOWARNINGDONACION);
                aviso.setHeaderText(HEADERWARNINGDONACION);
                aviso.setContentText(CONTENTWARNINGDONACION);

                break;
            case 'T': // WARNING -> AVISO TITULAR YA EXISTENTE
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOTITULAREXISTENTE);
                aviso.setHeaderText(HEADERTITULAREXISTENE);
                aviso.setContentText(CONTENTTITULAREXISTENTE);

                break;
            case 'M': // WARNING -> AVISO TITULAR YA EXISTENTE
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOINFO);
                aviso.setHeaderText(HEADERTEXTINFOB);
                aviso.setContentText(CONTENTETEXTINFOB);

                break;
            case 'L': // INFORMATIOM -> AVISO TITULAR ANYIADIDO CORRECTAMENTE
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(TITULOTITULARCORRECTO);
                aviso.setHeaderText(HEADERTITULARCORRECTO);
                aviso.setContentText(CONTENTTITULARCORRECTO);

                break;
            case 'R': // WARNING -> AVISO NO HA ELEGIDO TITULAR 
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOTITULARNOELEGIDO);
                aviso.setHeaderText(HEADERTITULARNOELEGIDO);
                aviso.setContentText(CONTENTTITULARNOELEGIDO);

                break;

            case 'B': // WARNING -> AVISO ESTAS EN BANCARROTA
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOBANCARROTA);
                aviso.setHeaderText(HEADERBANCARROTA);
                aviso.setContentText(CONTENTBANCARROTA);

                break;
            case 'Q': // CONFIRMACION -> ELIMINAR TITULAR
                aviso.setAlertType(Alert.AlertType.CONFIRMATION);
                aviso.setTitle(TITULOINFO);
                aviso.setHeaderText(HEADERTEXTINFOC);
                aviso.setContentText(CONTENTETEXTINFOC);

                break;
            case 'F': // WARNING -> NIF INCORRECTO
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOWARNINGNIF);
                aviso.setHeaderText(HEADERWARNINGNIF);
                aviso.setContentText(CONTENTWARNINGNIF);

                break;
            case 'Z': // WARNING -> FALLO INTEGRIDAD BD
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOFALLOINTEGRIDAD);
                aviso.setHeaderText(HEADERFALLOINTEGRIDAD);
                aviso.setContentText(CONTENTFALLOINTEGRIDAD);

                break;
            case 'P': // INFO -> IMPORTACION BBDD
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(TITULOIMPORTADOCORRECTOBD);
                aviso.setHeaderText("");
                aviso.setContentText(CONTENTIMPORTADOCORRECTOBD);

                break;
            case 'Y': // INFO -> EXPORTACION BBDD
                aviso.setAlertType(Alert.AlertType.INFORMATION);
                aviso.setTitle(TITULOEXPORTADOCORRECTOBD);
                aviso.setHeaderText("");
                aviso.setContentText(CONTENTEXPORTADOCORRECTOBD);

                break;
            case 'X': // WARNING -> DIRECTORIO NULO
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULODIRECTORIONULO);
                aviso.setHeaderText("");
                aviso.setContentText(CONTENTDIRECTORIONULO);

                break;
            case 'K': // WARNING -> FALLO EN LA CONEXION DE LA BD
                aviso.setAlertType(Alert.AlertType.WARNING);
                aviso.setTitle(TITULOCONEXIONFALLIDABD);
                aviso.setHeaderText("");
                aviso.setContentText(CONTENTCONEXIONFALLIDABD);

                break;

            default:

                throw new AssertionError();

        }

    }

}
