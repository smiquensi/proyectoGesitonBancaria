/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import modelo.Movimiento;

/**
 *
 * @author Enrique
 * @author SantiMiquel
 */
public class Archivo {

    Aviso aviso = new Aviso('W');

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    // METODO PARA IMPORTAR ARCHIVOS
    public File importarArchivo() {
        boolean seguir = true;
        File fichero = null;
        while (seguir) {
            // creamos un nuevo objeto filechoser
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));

            //elegimos la ruta de apertura default en ava jcombobox arraylist
            String userDir = System.getProperty("user.home");
            fileChooser.getSelectedExtensionFilter();
            fileChooser.setInitialDirectory(new File(userDir + "/documents"));

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

    // METODO PARA EXPORTAR ARCHIVOS
    public void exportarArchivo(List<Movimiento> movimiento, String nombrePersona, String tipoMovimiento, String fecha) {

        //directorio
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccione un directorio");

        File directorio = directoryChooser.showDialog(null);

        String directorioString = directorio.getAbsolutePath() + "/" + nombrePersona;

        File directorioFinal = new File(directorioString);

        if (!directorioFinal.exists()) {
            directorioFinal.mkdir();

        }

        File file = new File(directorioString + "/" + nombrePersona + " " + tipoMovimiento + " " + fecha + " Movimientos.txt");

        //fichero
        Path archivo = Paths.get(directorioString + "/" + nombrePersona + " " + tipoMovimiento + " " + fecha + " Movimientos.txt");

        if (!directorioFinal.exists()) {
            directorioFinal.mkdir();
        }
        if (file.exists()) {
            file.delete();

        }

        try (BufferedWriter out = Files.newBufferedWriter(archivo,
                Charset.defaultCharset(),
                StandardOpenOption.CREATE)) {
            out.write("fecha#dni#importe#motivo");
            out.newLine();
            for (Movimiento tmp : movimiento) {
                out.write(tmp.getFecha().format(formatter) + "#" + tmp.getDni() + "#" + tmp.getCantidad() + "€#" + tmp.getMotivo() + "#" + tmp.getTipo()); //escribimos la ñ
                out.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero");//falta aviso
        }

    }

}
