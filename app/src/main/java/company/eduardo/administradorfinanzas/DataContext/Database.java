package company.eduardo.administradorfinanzas.DataContext;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;


import company.eduardo.administradorfinanzas.DataContext.Dao.CategoriaEntradasDao;
import company.eduardo.administradorfinanzas.DataContext.Dao.CategoriaSalidasDao;
import company.eduardo.administradorfinanzas.DataContext.Dao.CategoriasCuentasDao;
import company.eduardo.administradorfinanzas.DataContext.Dao.CuentasDao;
import company.eduardo.administradorfinanzas.DataContext.Dao.EntradasDao;
import company.eduardo.administradorfinanzas.DataContext.Dao.PresupuestosDao;
import company.eduardo.administradorfinanzas.DataContext.Dao.SalidasDao;
import company.eduardo.administradorfinanzas.DataContext.Dao.SalidasPresupuestoDao;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Presupuestos;
import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.DataContext.Entities.SalidasPresupuesto;

@android.arch.persistence.room.Database(entities = {CategoriaEntradas.class, CategoriaSalidas.class, CategoriasCuentas.class, Cuentas.class, Entradas.class, Presupuestos.class, Salidas.class, SalidasPresupuesto.class}, version = 2)
@TypeConverters({DateTypeConverter.class})
public abstract class Database extends RoomDatabase {

    public abstract CategoriasCuentasDao categoriasCuentasDao();
    public abstract CategoriaEntradasDao categoriaEntradasDao();
    public abstract CategoriaSalidasDao categoriaSalidasDao();
    public abstract CuentasDao cuentasDao();
    public abstract EntradasDao entradasDao();
    public abstract PresupuestosDao presupuestosDao();
    public abstract SalidasDao salidasDao();
    public abstract SalidasPresupuestoDao salidasPresupuestoDao();

    private static volatile Database INSTANCE;

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "Database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
