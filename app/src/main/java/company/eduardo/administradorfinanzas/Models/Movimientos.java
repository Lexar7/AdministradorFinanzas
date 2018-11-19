package company.eduardo.administradorfinanzas.Models;

public class Movimientos {
    private String Nombre;
    private String Tipo;
    private Double Cantidad;

    public Movimientos(){

    }

    public Movimientos( String nombre, String tipo, Double cantidad){
        this.Nombre=nombre;
        this.Tipo=tipo;
        this.Cantidad=cantidad;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setCantidad(Double cantidad) {
        Cantidad = cantidad;
    }

    public Double getCantidad() {
        return Cantidad;
    }
}
