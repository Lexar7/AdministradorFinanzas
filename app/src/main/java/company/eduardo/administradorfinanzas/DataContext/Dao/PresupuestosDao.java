package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Presupuestos;

@Dao
public interface PresupuestosDao {

    @Insert
    void insert(Presupuestos presupuestos);

    @Update
    void update(Presupuestos presupuestos);

    @Query("DELETE FROM Presupuestos")
    void deleteAll();

    @Query("SELECT * FROM Presupuestos")
    LiveData<List<Presupuestos>> getAll();
}
