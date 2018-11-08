package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;

@Dao
public interface EntradasDao {

    @Insert
    void insert(Entradas entradas);

    @Update
    void update(Entradas entradas);

    @Query("DELETE FROM Entradas")
    void deleteAll();

    @Query("SELECT * FROM Entradas")
    LiveData<List<Entradas>> getAll();
}
