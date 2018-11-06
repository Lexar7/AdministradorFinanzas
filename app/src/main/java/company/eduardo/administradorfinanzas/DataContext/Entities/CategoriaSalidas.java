package company.eduardo.administradorfinanzas.DataContext.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "CategoriaSalidas")
public class CategoriaSalidas {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdCategoria")
    private Integer _idCategoria;

    @NonNull
    @ColumnInfo(name = "Name")
    private String _name;


    public CategoriaSalidas(@NonNull String name) {
        _name = name;
    }

    @NonNull
    public Integer getIdCategoria() {
        return _idCategoria;
    }

    @NonNull
    public String getName() {
        return _name;
    }
}
