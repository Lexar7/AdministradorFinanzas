package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;

@Dao
public interface CategoriaSalidasDao {

    @Insert
    void insert(CategoriaSalidas categoriaSalidas);

    @Query("DELETE FROM CategoriaSalidas")
    void deleteAll();

    @Query("SELECT * FROM CategoriaSalidas")
    LiveData<List<CategoriaSalidas>> getAll();
}
