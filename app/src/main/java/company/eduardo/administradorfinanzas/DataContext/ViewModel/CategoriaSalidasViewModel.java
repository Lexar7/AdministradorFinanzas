package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriaSalidasRepository;

public class CategoriaSalidasViewModel   extends AndroidViewModel {

    private CategoriaSalidasRepository repository;

    private LiveData<List<CategoriaSalidas>> listLiveData;

    public CategoriaSalidasViewModel (Application application) {
        super(application);
        repository = new CategoriaSalidasRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<CategoriaSalidas>> getAll() { return listLiveData; }

    public void insert(CategoriaSalidas categoriaSalidas) { repository.insert(categoriaSalidas); }

}
