package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;

@Dao
public interface CategoriaSalidasDao {

    @Insert
    void insert(CategoriaSalidas categoriaSalidas);

    @Update
    void update(CategoriaSalidas categoriaSalidas);

    @Delete
    void delete(CategoriaSalidas categoriaSalidas);

    @Query("DELETE FROM CategoriaSalidas")
    void deleteAll();

    @Query("SELECT * FROM CategoriaSalidas")
    LiveData<List<CategoriaSalidas>> getAll();

    @Query("SELECT * FROM CategoriaSalidas WHERE IdCategoria =:Id")
    LiveData<CategoriaSalidas> getOne(int Id);
}
