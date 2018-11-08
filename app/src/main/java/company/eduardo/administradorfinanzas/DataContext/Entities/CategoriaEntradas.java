package company.eduardo.administradorfinanzas.DataContext.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "CategoriaEntradas")
public class CategoriaEntradas {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdCategoria")
    private Integer _idCategoria;

    @NonNull
    @ColumnInfo(name = "Name")
    private String _name;

    @NonNull
    @ColumnInfo(name = "Image")
    private String _image;

    public CategoriaEntradas(@NonNull String name, @NonNull String image) { _name = name; _image = image;}

    public void set_idCategoria(Integer _idCategoria){
        this._idCategoria = _idCategoria;
    }

    @NonNull
    public Integer getIdCategoria() {
        return _idCategoria;
    }

    @NonNull
    public String getName() {
        return _name;
    }

    @NonNull
    public String getImage() {
        return _image;
    }
}
