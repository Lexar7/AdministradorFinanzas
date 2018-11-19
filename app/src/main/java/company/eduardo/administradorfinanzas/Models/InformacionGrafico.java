package company.eduardo.administradorfinanzas.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InformacionGrafico {
    private int Id;
    private String Descripcion;
    private Double Cantidad;

    public  InformacionGrafico(){
        this.Id =0;
        this.Descripcion ="";
        this.Cantidad=0.0;
    }
    public InformacionGrafico(@NonNull int _Id, @Nullable String _Descripcion, @Nullable Double _Cantidad){
        this.Id = _Id;
        this.Descripcion = _Descripcion;
        this.Cantidad = _Cantidad;
    }

    public int getId() {
        return Id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public Double getCantidad() {
        return Cantidad;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setCantidad(Double cantidad) {
        Cantidad = cantidad;
    }
}
