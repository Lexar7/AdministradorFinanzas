package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriaEntradasRepository;

public class CategoriaEntradasViewModel  extends AndroidViewModel {

    private CategoriaEntradasRepository repository;

    private LiveData<List<CategoriaEntradas>> listLiveData;

    public CategoriaEntradasViewModel (Application application) {
        super(application);
        repository = new CategoriaEntradasRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<CategoriaEntradas>> getAll() { return listLiveData; }

    public void insert(CategoriaEntradas categoriaEntradas) { repository.insert(categoriaEntradas); }

    public void update(CategoriaEntradas categoriaEntradas){ repository.update(categoriaEntradas);}
}
