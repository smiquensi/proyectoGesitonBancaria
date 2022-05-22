package auxiliar;

import static com.mysql.cj.Messages.getString;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * @author Enrique Ferrer
 */
public class BancoBD implements Initializable {

    private String bd = "bancoES";
    private String mensaje;
    private String login = "root";
    private String password = "DAM1";
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

    }
    
// METODO PARA CONCECTAR A LA BASE DE DATOS
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
// METODO PARA DESCONCECTAR A LA BASE DE DATOS

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
    
// METODO PARA ALMACENAR CUENTAS BANCARIAS EN LA BASE DE DATOS
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

// METODO QUE ALMACENA MOVIMIENTOS EN LA BASE DE DATOS
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
    
// METODO PARA RECOGER LA INFORMACION DE LA BASE DE DATOS Y CREAR NUEVOS MOVIMENTOS
    public Set<Movimiento> listarMovimientos() throws SQLException {
        sentencia = "select * from movimientos";
        PreparedStatement ps = conn.prepareStatement(sentencia, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            //CREAMOS EL OBJETO COMUNIDAD CON LOS DATOS DEL RESULSET
            Movimiento movimiento = new Movimiento (rs.getString("nifMov"), rs.getDouble("cantidad"), rs.getString("motivo"), getString("tipo").charAt(0));

            //ALMACENAMOS CADA OBJETO COMUNIDAD EN EL CONJUNTO
            listadoMovimientos.add(movimiento);
        }
        return listadoMovimientos;

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

}
