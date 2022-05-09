/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import com.proyecto.bancobase.PrimaryController;

/**
 *
 * @author santimiquel
 */
public class TextControl {

    private String txt;

    public TextControl(String txt) {
        this.txt = txt;
    }

    public boolean formatoNif(String nif) {
        boolean esCorrecto = false;
        if (nif.matches("[0-9]{8}+\\-?\\s?+[a-z,A-Z]{1}")) {
            esCorrecto = true;
        }

        return esCorrecto;
    }

    public boolean formatoIban(String iban) {
        boolean esCorrecto = false;
        if (iban.matches("[a-zA-Z]{2}[0-9]{20}")) { // Comprobar si funciona, he pillado la expesion de internet
            esCorrecto = true;
        }
        return false;
    }

    public boolean formatoNombre(String nombre) {
        boolean esCorrecto = false;
        if (nombre.matches("[a-zA-Z]{3,}")) { // Comprobar si funciona, es para min. un nombre de 3 letras. 
            esCorrecto = true;
        }
        return false;
    }
        // METODO PARA PARTIR POR CELDAS EL STRING PASADO POR CUENTABANCARAIA.LISTARMOVIMIESTOS
        public void splitString() {
        String[] lineas = PrimaryController.getCuentaElegida().listarMovimientos('T').split("\\r?\\n");

        for (int i = 0; i < lineas.length; i++) {

            String[] splited = lineas[i].split("\\s+");
            for (int j = 0; j < splited.length; j++) {

            }

        }
    }
        public void cortaPalabras(){
            
      
    }
}
