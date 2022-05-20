/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import modelo.CuentaBancaria;
import modelo.Movimiento;

/**
 *
 * @author santimiquel
 */
public class BancoBD {

    private String bd = "bancoES";
    private String mensaje;
    private String login = "root";
    private String password = "DAM1";
    private String url = "jdbc:mysql://localhost:3306/" + bd;
    private Boolean conexionCreada, insertarCuenta;
    private Connection conn;
    private String sentencia;

  

    public boolean conectarBd() throws Exception {

        try {
            conn = DriverManager.getConnection(url, login, password);
            conexionCreada = true;

        } catch (SQLException e) {
            conexionCreada = false;
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

        sentencia = "INSERT INTO CuentasBancarias (nCuenta, nombre, nif, donaciones, saldo) VALUES (?,?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(sentencia);
        ps.setString(1, cuenta.getNumCuenta());
        ps.setString(2, cuenta.getTitulares().iterator().next().getNombre());
        ps.setString(3, cuenta.getTitulares().iterator().next().getNif());
        ps.setDouble(4, cuenta.getDonaciones());
        ps.setDouble(5, cuenta.getSaldo());
        int filasInsertadas = ps.executeUpdate();
        ps.clearParameters();

        return filasInsertadas;

    }

    public int almacenarMovimiento(Movimiento movimiento) throws SQLException {

        sentencia = "INSERT INTO Movimientos (fecha, nifMov, cantidad, motivo, tipo) VALUES (?,?,?,?,?);";

        PreparedStatement ps = conn.prepareStatement(sentencia);

        ps.setTimestamp(1,getCurrentTimeStamp());
        ps.setString(2, movimiento.getDni());
        ps.setDouble(3, movimiento.getCantidad());
        ps.setString(4, movimiento.getMotivo());
        ps.setString(5, movimiento.getTipo()+"");

        int filasInsertadas = ps.executeUpdate();
        ps.clearParameters();

        return filasInsertadas;

    }
    
    
    private static java.sql.Timestamp getCurrentTimeStamp() {
    java.util.Date today = new java.util.Date();
    return new java.sql.Timestamp(today.getTime());
}

}
