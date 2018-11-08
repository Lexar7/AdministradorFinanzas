package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.EntradasRepository;

public class EntradasViewModel extends AndroidViewModel {

    private EntradasRepository repository;

    private LiveData<List<Entradas>> listLiveData;

    public EntradasViewModel (Application application) {
        super(application);
        repository = new EntradasRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<Entradas>> getAll() { return listLiveData; }

    public void insert(Entradas entrada) { repository.insert(entrada); }

    public void update(Entradas entradas){ repository.update(entradas);}

}
