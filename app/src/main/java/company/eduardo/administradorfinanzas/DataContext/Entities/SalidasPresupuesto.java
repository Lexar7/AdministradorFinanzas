package company.eduardo.administradorfinanzas.DataContext.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "SalidasPresupuesto")
public class SalidasPresupuesto {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdSalidaPresupuesto")
    private Integer _idSalidaPresupuesto;

    @NonNull
    @ColumnInfo(name = "IdPresupuesto")
    private Integer _idPresupuesto;


    @NonNull
    @ColumnInfo(name = "IdCategoria")
    private Integer _idCategoria;

    @NonNull
    @ColumnInfo(name = "Saldo")
    private Double _saldo;


    public SalidasPresupuesto(@NonNull Integer idPresupuesto, @NonNull Integer idCategoria, @NonNull Double saldo) {
        _idPresupuesto = idPresupuesto;
        _idCategoria = idCategoria;
        _saldo = saldo;
    }

    public void setIdSalidaPresupuesto(Integer _IdSalidaPresupuesto){
        this._idSalidaPresupuesto = _IdSalidaPresupuesto;
    }

    @NonNull
    public Integer getIdSalidaPresupuesto() {
        return _idSalidaPresupuesto;
    }

    @NonNull
    public Integer getIdPresupuesto() {
        return _idPresupuesto;
    }

    @NonNull
    public Integer getIdCategoria() {
        return _idCategoria;
    }

    @NonNull
    public Double get_saldo() {
        return _saldo;
    }
}
