package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;

@Dao
public interface CategoriasCuentasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoriasCuentas categoriasCuentas);

    @Query("DELETE FROM CategoriasCuentas")
    void deleteAll();

    @Query("SELECT * FROM CategoriasCuentas")
    LiveData<List<CategoriasCuentas>> getAll();
}
