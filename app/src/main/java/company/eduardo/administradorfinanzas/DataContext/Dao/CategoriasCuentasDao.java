package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;

@Dao
public interface CategoriasCuentasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoriasCuentas categoriasCuentas);

    @Update
    void update(CategoriasCuentas categoriasCuentas);

    @Delete
    void delete(CategoriasCuentas categoriasCuentas);

    @Query("DELETE FROM CategoriasCuentas")
    void deleteAll();

    @Query("SELECT * FROM CategoriasCuentas")
    LiveData<List<CategoriasCuentas>> getAll();

    @Query("SELECT * FROM CategoriasCuentas WHERE IdCategoria =:Id")
    LiveData<CategoriasCuentas> getOne(int Id);
}
