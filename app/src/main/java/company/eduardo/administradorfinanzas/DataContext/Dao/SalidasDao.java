package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Calendar;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;

@Dao
public interface SalidasDao {

    @Insert
    void insert(Salidas salidas);

    @Update
    void update(Salidas salidas);

    @Delete
    void delete(Salidas salidas);

    @Query("DELETE FROM Salidas")
    void deleteAll();

    @Query("SELECT * FROM Salidas")
    LiveData<List<Salidas>> getAll();

    @Query("SELECT * FROM Salidas WHERE Fecha BETWEEN :cal1 AND :cal2")
    LiveData<List<Salidas>> getAll(Calendar cal1, Calendar cal2);

    @Query("SELECT * FROM Salidas WHERE Fecha BETWEEN :cal1 AND :cal2 and IdCuenta==:Id")
    LiveData<List<Salidas>> getAll(Calendar cal1, Calendar cal2, int Id);

    @Query("SELECT * FROM Salidas WHERE IdSalidas ==:Id")
    LiveData<Salidas> getOne(int Id);
}
