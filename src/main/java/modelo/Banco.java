package modelo;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Banco {

  //ATRIBUTOS
  private String nombreBanco = "GRAN BANCO DE MISLATA";
  private final Map<String, CuentaBancaria> cuentasBancarias = new HashMap<>();

  //CONSTRUCTOR
  public Banco() {
    cargaDatosIniciales();
  }

  //GETTERS
  public String getNombreBanco() {
    return nombreBanco;
  }

  //SETTERS
  public void setNombreBanco(String nombreBanco) {
    this.nombreBanco = nombreBanco;

  }

  //MÉTODOS  
  private void cargaDatosIniciales() {

    //Titulares de cuentas y personas autorizadas para pruebas
    Persona titular1 = new Persona("11111111A", "Pau Fandos");
    Persona titular2 = new Persona("22222222B", "Jose Luis Coloma");
    Persona titular3 = new Persona("33333333C", "Raquel López");

   

    //cuentas bancarias
    CuentaBancaria cuenta1 = new CuentaBancaria("1234567899", titular1);
    CuentaBancaria cuenta2 = new CuentaBancaria("1345678910", titular2);
    CuentaBancaria cuenta3 = new CuentaBancaria("3456789124", titular3);
    

    
    //ingresos
    cuenta1.ingresar("11222333A",100, "regalo");
    cuenta2.ingresar("55444333S",500, "hipoteca");
    cuenta3.ingresar("11222333A",420, "prestamo");

    //retiros
    cuenta1.sacar("55444333S",100, "regalo");
    cuenta2.sacar("11222333A",50, "peluqueria");
    cuenta2.sacar("11222333A",100, "mercadona");
    cuenta3.sacar("55444333S",234, "portatil");
    cuenta3.sacar("11222333A",189, "regalo");
    cuenta3.sacar("55444333S",1000, "coche");

    //conjunto cuentas
    cuentasBancarias.put(cuenta1.getNumCuenta(), cuenta1);
    cuentasBancarias.put(cuenta2.getNumCuenta(), cuenta2);
    cuentasBancarias.put(cuenta3.getNumCuenta(), cuenta3);

   

  }

  
  
  //Método para encontrar una cuenta bancaria por su número de cuenta
  public CuentaBancaria localizaCC(long ncuenta) {
    if (cuentasBancarias.containsKey(ncuenta)) {
      return cuentasBancarias.get(ncuenta);
    } else {
      return null;
    }
  }
  
  
  //ALMACENA LA CUENTA CC PASADA POR PARAMETRO 
  public CuentaBancaria almacenarCC(CuentaBancaria cc){
    return cuentasBancarias.put(cc.getNumCuenta(), cc);
  }
  
  
  
  //DEVUELVE TODAS LAS   CUENTAS
  public Set<CuentaBancaria> listaCuentasBancarias(){
    return (Set<CuentaBancaria>) cuentasBancarias.values();
  }

}
