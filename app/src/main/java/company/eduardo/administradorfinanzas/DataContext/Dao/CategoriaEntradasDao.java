package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;

@Dao
public interface CategoriaEntradasDao {

    @Insert
    void insert(CategoriaEntradas categoriaEntradas);

    @Update
    void update(CategoriaEntradas categoriaEntradas);

    @Delete
    void delete(CategoriaEntradas categoriaEntradas);

    @Query("DELETE FROM CategoriaEntradas")
    void deleteAll();

    @Query("SELECT * FROM CategoriaEntradas")
    LiveData<List<CategoriaEntradas>> getAll();

    @Query("SELECT * FROM CategoriaEntradas WHERE IdCategoria =:Id")
    LiveData<CategoriaEntradas> getOne(int Id);
}
