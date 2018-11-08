package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.SalidasPresupuesto;

@Dao
public interface SalidasPresupuestoDao {

    @Insert
    void insert(SalidasPresupuesto salidasPresupuesto);

    @Update
    void update(SalidasPresupuesto salidasPresupuesto);

    @Query("DELETE FROM SalidasPresupuesto")
    void deleteAll();

    @Query("SELECT * FROM SalidasPresupuesto")
    LiveData<List<SalidasPresupuesto>> getAll();
}
