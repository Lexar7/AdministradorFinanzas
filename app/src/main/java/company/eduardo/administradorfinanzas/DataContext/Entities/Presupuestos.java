package company.eduardo.administradorfinanzas.DataContext.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Presupuestos")
public class Presupuestos {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdPresupuesto")
    private Integer _idPresupuesto;

    @NonNull
    @ColumnInfo(name = "MesPresupuesto")
    private Double _mesPresupuesto;


    public Presupuestos(@NonNull Double mesPresupuesto) {
        _mesPresupuesto = mesPresupuesto;
    }


    @NonNull
    public Integer get_idPresupuesto() {
        return _idPresupuesto;
    }

    @NonNull
    public Double get_mesPresupuesto() {
        return _mesPresupuesto;
    }
}
