package company.eduardo.administradorfinanzas.DataContext.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.Models.InformacionGrafico;

@Dao
public interface CuentasDao {

    @Insert
    void insert(Cuentas cuentas);

    @Update
    void update(Cuentas cuentas);

    @Delete
    void delete(Cuentas cuentas);

    @Query("DELETE FROM Cuentas")
    void deleteAll();

    @Query("SELECT * FROM Cuentas")
    LiveData<List<Cuentas>> getAll();

    @Query("SELECT * FROM Cuentas WHERE IdCuenta =:Id")
    LiveData<Cuentas> getOne(int Id);

    @Query("SELECT 1 as 'Id', 'Ingresos' as 'Descripcion', SUM(Saldo) as 'Cantidad' FROM Entradas " +
            "INNER JOIN CategoriaEntradas ON Entradas.IdCategoria = CategoriaEntradas.IdCategoria " +
            "WHERE IdCuenta=:Id GROUP BY 'Id', 'Descripcion' UNION" +
            " SELECT 3 as 'Id', 'Gastos' as 'Descripcion', SUM(Saldo) as 'Cantidad' FROM Salidas " +
            "INNER JOIN CategoriaSalidas ON Salidas.IdCategoria = CategoriaSalidas.IdCategoria" +
            " WHERE IdCuenta=:Id GROUP BY 'Id', 'Descripcion'")
    LiveData<List<InformacionGrafico>> getGraphic(int Id);
}
