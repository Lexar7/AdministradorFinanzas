package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;

@Dao
public interface CuentasDao {

    @Insert
    void insert(Cuentas cuentas);

    @Update
    void update(Cuentas cuentas);

    @Query("DELETE FROM Cuentas")
    void deleteAll();

    @Query("SELECT * FROM Cuentas")
    LiveData<List<Cuentas>> getAll();
}
