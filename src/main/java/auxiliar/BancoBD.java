/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import modelo.CuentaBancaria;
import modelo.Movimiento;

/**
 *
 * @author santimiquel
 */
public class BancoBD implements Initializable {

    private String bd = "bancoES";
    private String mensaje;
    private String login = "root";
    private String password = "DAM1"; // san608921482
    private String url = "jdbc:mysql://localhost:3306/" + bd;
    private String nCuenta;
    private Boolean conexionCreada, insertarCuenta;
    private Connection conn;
    private String sentencia;
    private Set<Movimiento> listadoMovimientos = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conectarBd();
        } catch (Exception ex) {
            System.out.println("FALLO DE CONEXION");
        }
       ;
    }

    public boolean conectarBd() throws Exception {

        try {
            conn = DriverManager.getConnection(url, login, password);
            conexionCreada = true;

        } catch (SQLException e) {
            conexionCreada = false;
            System.out.println("no vaaaaaaaa");
            mensaje = e.getMessage();
        }
        return conexionCreada;
    }

    public boolean desconectarBd() {

        boolean isClosed;
        try {
            conn.close();
            isClosed = true;

        } catch (SQLException e) {
            isClosed = false;
            mensaje = e.getMessage();

        }
        return isClosed;

    }

    public int almacenarCuenta(CuentaBancaria cuenta) throws SQLException {
        nCuenta = cuenta.getNumCuenta();
        sentencia = "INSERT INTO CuentasBancarias (nCuenta, nombre, nif, donaciones, saldo) VALUES (?,?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(sentencia);
        ps.setString(1, nCuenta);
        ps.setString(2, cuenta.getTitulares().iterator().next().getNombre());
        ps.setString(3, cuenta.getTitulares().iterator().next().getNif());
        ps.setDouble(4, cuenta.getDonaciones());
        ps.setDouble(5, cuenta.getSaldo());
        int filasInsertadas = ps.executeUpdate();
        ps.clearParameters();

        return filasInsertadas;

    }

    // ESTOS METODOS DEBERIAN DE COMPROBAR SI NO SE REPITEN EN LA BD
    public int almacenarMovimiento(Movimiento movimiento) throws SQLException {

        sentencia = "INSERT INTO Movimientos (fecha, nCuentaMov, nifMov, cantidad, motivo, tipo) VALUES (?,?,?,?,?,?);";

        PreparedStatement ps = conn.prepareStatement(sentencia);

        ps.setTimestamp(1, getCurrentTimeStamp());
        ps.setString(2, nCuenta);
        ps.setString(3, movimiento.getDni());
        ps.setDouble(4, movimiento.getCantidad());
        ps.setString(5, movimiento.getMotivo());
        ps.setString(6, movimiento.getTipo() + "");

        int filasInsertadas = ps.executeUpdate();
        listadoMovimientos.add(movimiento);

        ps.clearParameters();

        return filasInsertadas;

    }
    
    

    public Set<Movimiento> listarMovimientos() {

        return listadoMovimientos;

    }

    
    
    
    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

}
