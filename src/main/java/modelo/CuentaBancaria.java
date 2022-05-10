package modelo;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

import java.util.Deque;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

public class CuentaBancaria {

    public final int LIMITENUMROJOS = -50;
    public final int LIMITEHACIENDA = 3000;

    private String numCuenta;
    private Set<Persona> titulares = new HashSet<>();
    private double saldo;
    private double donaciones = 0;
    private Deque<Movimiento> movimientos = new ArrayDeque<>();

    public CuentaBancaria(String ncuenta, Persona titular) {
        this.numCuenta = ncuenta;
        titulares.add(titular);
        this.saldo = 0;
    }

    public double getDonaciones() {
        return donaciones;
    }

    public void sumaDonacion(double donacion) {
        this.donaciones += donacion;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getSaldoFormateado() {

        DecimalFormat f = new DecimalFormat("###,###.##€");
        return f.format(saldo);
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Set<Persona> getTitulares() {
        return titulares;
    }

    public void setTitulares(Set<Persona> titulares) {
        this.titulares = titulares;
    }

    public int ingresar(String dni, double cantidad, String motivo) {
        LocalDateTime hoy = LocalDateTime.now();
        Movimiento mov = new Movimiento(hoy, dni, cantidad, motivo, 'I');
        if (cantidad > 0) {
            saldo += cantidad;
            movimientos.add(mov);
            return (cantidad >= LIMITEHACIENDA) ? 1 : 0; //1 avisar a hacienda    0 ingreso ok
        }

        return -1; //-1 cantidad negativa
    }

// OTRA FORMA CODIFICANDO ERRORES
    public char sacar(String dni, double cantidad, String motivo) {
        char sacado = 'X'; //NO hay dinero suficiente, se superan los -50€

        if (saldo - cantidad >= LIMITENUMROJOS) {
            saldo = saldo - cantidad;
            sacado = (saldo < 0) ? 'R' : 'V'; //'R'-números rojos  'V'-extracción ok
            LocalDateTime hoy = LocalDateTime.now();
            Movimiento mov = new Movimiento(hoy, dni, cantidad, motivo, 'E');
            movimientos.add(mov);
        }

        return sacado;

    }

    public String informacionCuenta() {
        String resultado = "Nº cuenta: " + numCuenta + " - " + titulares + " \n";

        resultado += "Saldo: " + getSaldoFormateado();

        return resultado;
    }

    public Persona esTitular(String dniComprobar) {

        for (Persona titular : titulares) {
            if (titular.igual(dniComprobar)) {
                return titular;
            }
        }
        return null;
    }

    public boolean nuevoTitular(String nif, String nombre) {
        boolean incluido = false;
        Persona titular;
        Persona existeTitular = esTitular(nif);

        if (existeTitular == null) { //PARA EVITAR DUPLICADOS
            titular = new Persona(nif, nombre);
            titulares.add(titular);
            incluido = true;
        }
        return incluido;

    }

    public String eliminaTitular(String dni) {
        Persona existeTitular = esTitular(dni);
        String respuesta = "no eliminado porque NO puede quedarse con 0 titulares";

        if (titulares.size() > 1) { //Debe haber mas de 1 titular
            if (existeTitular != null) {
                titulares.remove(existeTitular);
                respuesta = "Eliminado el titular con nif " + dni;
            } else {
                respuesta = "No existe titular";
            }
        }
        return respuesta;
    }

    public String listarMovimientos(char tipoMovBuscado) {
        String listado = "LISTADO MOVIMIENTOS DE " + getNumCuenta() + "\n";
        if (tipoMovBuscado == 'T') {
            for (Movimiento movimiento : movimientos) {
                listado += movimiento + "\n";
            }
        } else {
            for (Movimiento movimiento : movimientos) {
                if (movimiento.getTipo() == tipoMovBuscado) {
                    listado += movimiento + "\n";
                }
            }
        }

        return listado;
    }

    // ESTE METODO LO HE CREADO POR QUE PIDE EN EL ENUNCIADO UNA TABLEVIEW DE OBJETOS MOVIMIENTO
    public List listarObjectoMovimientos(char tipoMovBuscado) {
//        String listado = "LISTADO MOVIMIENTOS DE " + getNumCuenta() + "\n";
        List<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        if (tipoMovBuscado == 'T') {
            for (Movimiento movimiento : movimientos) {
                listaMovimientos.add(movimiento);
            }
        } else {
            for (Movimiento movimiento : movimientos) {
                if (movimiento.getTipo() == tipoMovBuscado) {
                    listaMovimientos.add(movimiento);

                }
            }
        }

        return listaMovimientos;
    }

    //Añado metodo toString
    @Override
    public String toString() {
        return "NºCuenta ->" + numCuenta + ", titulares ->" + titulares + ", saldo ->" + saldo + '}';
    }

}
