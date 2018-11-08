package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;

@Dao
public interface SalidasDao {

    @Insert
    void insert(Salidas salidas);

    @Update
    void update(Salidas salidas);

    @Query("DELETE FROM Salidas")
    void deleteAll();

    @Query("SELECT * FROM Salidas")
    LiveData<List<Salidas>> getAll();
}
