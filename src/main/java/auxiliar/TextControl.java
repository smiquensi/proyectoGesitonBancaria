/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import modelo.Movimiento;

/**
 *
 * @author santimiquel
 * @author Enrique
 */
public class TextControl {

    private String txt;
    private static List<String> arrayLineas = new ArrayList();
    private static List<Movimiento> arrayMovimientosImportados = new ArrayList();
    boolean isFirst = true;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    private static String cantidad;

    public TextControl(String txt) {
        this.txt = txt;
    }

    // METODO QUE COMPRUEBA SI UN NIF TIENE EL FORMATO CORRECTO
    public static boolean formatoNif(String nif) {
        boolean esCorrecto = false;
        if (nif.matches("[0-9]{8}+\\-?\\s?+[a-z,A-Z]{1}")) {
            esCorrecto = true;
        }

        return esCorrecto;
    }

//    public boolean formatoIban(String iban) {
//        boolean esCorrecto = false;
//        if (iban.matches("[a-zA-Z]{2}[0-9]{20}")) { // Comprobar si funciona, he pillado la expesion de internet
//            esCorrecto = true;
//        }
//        return false;
//    }
//    public boolean formatoNombre(String nombre) {
//        boolean esCorrecto = false;
//        if (nombre.matches("[a-zA-Z]{3,}")) { // Comprobar si funciona, es para min. un nombre de 3 letras. 
//            esCorrecto = true;
//        }
//        return false;
//    }
    
    // METODO QUE PARTE STRING POR #
    public static List<Movimiento> splitAlmohadilla(File archivo) {
        boolean esPrimera = true; // evitamos que nos cree la primera que son los encabezados
        arrayLineas.clear();
        arrayMovimientosImportados.clear();

        try ( Stream<String> contenidoArchivo = Files.lines(archivo.toPath(), Charset.defaultCharset())) {
            Iterator<String> it = contenidoArchivo.iterator();
            while (it.hasNext()) {

                if (!esPrimera) {
                    arrayLineas.add(it.next());
                } else {
                    it.next();
                    esPrimera = false;
                }

            }
        } catch (IOException ex) {
            System.out.println("Error en la lectura del archivo");
        }

        Iterator<String> it = arrayLineas.iterator();

        while (it.hasNext()) {
            StringTokenizer st = new StringTokenizer(it.next(), "#");

            while (st.hasMoreTokens()) {

                String fecha = st.nextToken();
                if (fecha.length() != 8) {
                    fecha = "0" + fecha;
                }
                LocalDateTime fechaFormat = LocalDateTime.parse(fecha + " " + LocalTime.now().format(dtf), formatter);
                String dni = st.nextToken();

                cantidad = st.nextToken();

                cantidad = cantidad.substring(0, cantidad.length() - 3);

                if (cantidad.contains("--")) {
                    cantidad = "0.0";

                }
                double importe = Double.parseDouble(cantidad);

                String motivo = st.nextToken();

                char tipoOperacion = st.nextToken().charAt(0);

                Movimiento mov = new Movimiento(fechaFormat, dni, importe, motivo, tipoOperacion);
                arrayMovimientosImportados.add(mov);
            }
        }

        return arrayMovimientosImportados;

    }
}
