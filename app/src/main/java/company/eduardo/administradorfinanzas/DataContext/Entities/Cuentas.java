package company.eduardo.administradorfinanzas.DataContext.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import company.eduardo.administradorfinanzas.DataContext.DateTypeConverter;

@Entity(tableName = "Cuentas")
public class Cuentas {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdCuenta")
    private Integer _idCuenta;

    @NonNull
    @ColumnInfo(name = "NombreCuenta")
    private String _nombreCuenta;

    @NonNull
    @ColumnInfo(name = "SaldoInicial")
    private Double _saldoInicial;

    @NonNull
    @ColumnInfo(name = "IdCategoria")
    private Integer _idCategoria;

    @NonNull
    @ColumnInfo(name = "FechaCreacion")
    @TypeConverters(DateTypeConverter.class)
    private Calendar _fechaCreacion;

    public Cuentas(@NonNull String nombreCuenta, @NonNull Double saldoInicial, @NonNull Integer idCategoria, @NonNull Calendar fechaCreacion) {
        _nombreCuenta = nombreCuenta;
        _saldoInicial = saldoInicial;
        _idCategoria = idCategoria;
        _fechaCreacion = fechaCreacion;
    }


    public void setIdCuenta(Integer _IdCuenta){
        this._idCuenta = _IdCuenta;
    }

    @NonNull
    public Integer getIdCuenta() {
        return _idCuenta;
    }

    @NonNull
    public String getNombreCuenta() {
        return _nombreCuenta;
    }

    @NonNull
    public Double getSaldoInicial() {
        return _saldoInicial;
    }

    @NonNull
    public Integer getIdCategoria() {
        return _idCategoria;
    }

    @NonNull
    public Calendar getFechaCreacion() {
        return _fechaCreacion;
    }
}
