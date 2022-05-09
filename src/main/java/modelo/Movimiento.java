package modelo;

import java.time.LocalDateTime;

public class Movimiento {

    private LocalDateTime fecha;
    private String dni;
    private double cantidad; // positivo es ingreso, negativo es extraccion
    private String motivo;
    private char tipo; //'E' extraccion  -  'I' ingreso

    public Movimiento(LocalDateTime fecha, String dni, double cantidad, String motivo, char tipo) {
        this.fecha = fecha;
        this.dni = dni;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.tipo = tipo;
    }

    public Movimiento(String dni, double cantidad, String motivo, char tipo) {
        this.dni = dni;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.tipo = tipo;
        fecha = LocalDateTime.now();
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public char getTipo() {
        return tipo;
    }

    public String getDni() {
        return dni;
    }

    public double getCantidad() {
        return cantidad;
    }

    // ESTE GETTER LO HE CREADO YO.
    public String getMotivo() {
        return motivo;
    }
   

    @Override
    public String toString() {
        char signo = ' ';
        if (tipo == 'E') {
            signo = '-';
        }
        return fecha + "  " + signo + cantidad + "€  " + motivo;
    }
}
