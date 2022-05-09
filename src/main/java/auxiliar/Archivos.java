/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import com.proyecto.bancobase.PrimaryController;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

/**
 *
 * @author Enrique
 */
public class Archivos {

    Aviso aviso = new Aviso('W');

    private File ImportarArchivo() {
        boolean seguir = true;
        File fichero = null;
        while (seguir) {
            // creamos un nuevo objeto filechoser
            FileChooser fileChooser = new FileChooser();
            //elegimos la ruta de apertura default en 
            String userDir = System.getProperty("user.home");
            fileChooser.setInitialDirectory(new File(userDir + "/Documents"));

            fichero = fileChooser.showOpenDialog(null);

            //String fileString = fichero.getName();
            //String fileString = fileString.substring(fileString.indexOf(".");
            if (fichero == null) {
                lanzarAviso('W');
                break;

            } else {
                seguir = false;
            }

        }
        return fichero;
    }

    private void lanzarAviso(char caracter) {
        aviso.cambioAviso(caracter);
        aviso.showAndWait();

    }

}
