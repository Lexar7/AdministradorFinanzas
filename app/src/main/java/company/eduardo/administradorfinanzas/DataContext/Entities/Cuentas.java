package company.eduardo.administradorfinanzas.DataContext.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

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
    private Date _fechaCreacion;

    public Cuentas(@NonNull String nombreCuenta, @NonNull Double saldoInicial, @NonNull Integer idCategoria, @NonNull Date fechaCreacion) {
        _nombreCuenta = nombreCuenta;
        _saldoInicial = saldoInicial;
        _idCategoria = idCategoria;
        _fechaCreacion = fechaCreacion;
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
    public Date getFechaCreacion() {
        return _fechaCreacion;
    }
}
