package company.eduardo.administradorfinanzas.DataContext.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

import company.eduardo.administradorfinanzas.DataContext.DateTypeConverter;

@Entity(tableName = "Salidas")
public class Salidas {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdSalidas")
    private Integer _idSalida;

    @NonNull
    @ColumnInfo(name = "Descripcion")
    private String _descripcion;

    @NonNull
    @ColumnInfo(name = "Saldo")
    private Double _saldo;

    @NonNull
    @ColumnInfo(name = "Fecha")
    @TypeConverters(DateTypeConverter.class)
    private Calendar _fecha;

    @NonNull
    @ColumnInfo(name = "IdCategoria")
    private Integer _idCategoria;

    @NonNull
    @ColumnInfo(name = "IdCuenta")
    private Integer _idCuenta;


    public Salidas(@NonNull String descripcion, @NonNull Double saldo, @NonNull Calendar fecha, @NonNull Integer idCategoria, @NonNull Integer idCuenta) {
        _descripcion = descripcion;
        _saldo = saldo;
        _fecha = fecha;
        _idCategoria = idCategoria;
        _idCuenta = idCuenta;
    }

    public void set_idSalida(@NonNull Integer _idSalida){
        this._idSalida = _idSalida;
    }

    @NonNull
    public Integer getIdSalida() {
        return _idSalida;
    }

    @NonNull
    public String getDescripcion() {
        return _descripcion;
    }

    @NonNull
    public Double getSaldo() {
        return _saldo;
    }

    @NonNull
    public Calendar getFecha() {
        return _fecha;
    }

    @NonNull
    public Integer getIdCategoria() {
        return _idCategoria;
    }

    @NonNull
    public Integer getIdCuenta() {
        return _idCuenta;
    }
}
