
package modelo;


public class Persona {

    private String nif;
    private String nombre;

      
    public Persona(String nif, String nombre) {
        this.nif = nif;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }
    public boolean igual(Persona p){
        boolean resultado=false;
        if(nif.equalsIgnoreCase(p.getNif())){
            resultado=true;
        }
        return resultado;
    }
    
     public boolean igual(String dni){
        boolean resultado=false;
        if(nif.equalsIgnoreCase(dni)){
            resultado=true;
        }
        return resultado;
    }
    
    
    @Override
    public String toString(){
        String resultado = nombre + "("+ nif + ")";
        return resultado;
    }
}
