package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.Calendar;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.DateTypeConverter;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.Models.InformacionGrafico;

@Dao
public interface EntradasDao {

    @Insert
    void insert(Entradas entradas);

    @Update
    void update(Entradas entradas);

    @Delete
    void delete(Entradas entradas);

    @Query("DELETE FROM Entradas")
    void deleteAll();

    @Query("SELECT * FROM Entradas")
    LiveData<List<Entradas>> getAll();

    @Query("SELECT * FROM Entradas WHERE Fecha <= :cal1")
    LiveData<List<Entradas>> getAll(Calendar cal1);

    @Query("SELECT * FROM Entradas WHERE Fecha BETWEEN :cal1 AND :cal2")
    LiveData<List<Entradas>> getAll(Calendar cal1, Calendar cal2);

    @Query("SELECT * FROM Entradas WHERE Fecha BETWEEN :cal1 AND :cal2 AND IdCuenta==:Id")
    LiveData<List<Entradas>> getAll(Calendar cal1, Calendar cal2, int Id);

    @Query("SELECT * FROM Entradas WHERE IdEntrada ==:Id")
    LiveData<Entradas> getOne(int Id);

    @Query("SELECT Entradas.IdCategoria as 'Id', Name as 'Descripcion', SUM(Saldo) as 'Cantidad' FROM Entradas " +
            "INNER JOIN CategoriaEntradas ON Entradas.IdCategoria = CategoriaEntradas.IdCategoria " +
            "WHERE IdCuenta=:Id GROUP BY Entradas.IdCategoria, Name")
    LiveData<List<InformacionGrafico>> getGraphic(int Id);

}
