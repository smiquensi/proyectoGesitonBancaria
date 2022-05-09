/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

/**
 *
 * @author santimiquel
 */
public class ControlInput {

    private String txt;

    public ControlInput(String txt) {
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
}
