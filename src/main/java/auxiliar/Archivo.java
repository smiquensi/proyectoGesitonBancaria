/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import com.proyecto.bancobase.PrimaryController;
import com.proyecto.bancobase.SecondaryController;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import modelo.Movimiento;

/**
 *
 * @author Enrique
 */
public class Archivo {

    Aviso aviso = new Aviso('W');

    public File importarArchivo() {
        boolean seguir = true;
        File fichero = null;
        while (seguir) {
            // creamos un nuevo objeto filechoser
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));

            //elegimos la ruta de apertura default en 
            String userDir = System.getProperty("user.home");
            fileChooser.getSelectedExtensionFilter();
            fileChooser.setInitialDirectory(new File(userDir + "/documents/NetBeansProjects/proyectoGesitonBancaria/datos"));

            fichero = fileChooser.showOpenDialog(null);

            if (fichero == null) {
                aviso.showAndWait(); // PERSONALIZAR AVISO

                break;

            } else {
                seguir = false;

            }

        }
        return fichero;
    }

    public void exportarArchivo(List<Movimiento> movimiento, String nombrePersona) {
        String directoryName =nombrePersona;

        Path archivo = Paths.get(directoryName + "/" + "Movimientos.txt");

        File file = new File(directoryName + "/" + "Movimientos.txt");
        File directorio = new File(nombrePersona);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        if (file.exists()) {
            file.delete();
        }

        try (BufferedWriter out = Files.newBufferedWriter(archivo,
                Charset.defaultCharset(),
                StandardOpenOption.CREATE)) {
            out.write("fecha#dni#importe#motivo");

            out.newLine();
            for (Movimiento emp : movimiento) {
                out.write(emp.getFecha() + "#" + emp.getDni() + "#" + emp.getCantidad() + "#" + emp.getMotivo() + "#" + emp.getTipo()); //escribimos la ñ
                out.newLine();
            }

            System.out.println("ARCHIVO CREADO CON EXITO \nREVISA EN EL DIRECTORIO DE ESTE PROYECTO");

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero");
        }

    }

}
