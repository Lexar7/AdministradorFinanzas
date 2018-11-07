package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.SalidasRepository;

public class SalidasViewModel extends AndroidViewModel {

    private SalidasRepository repository;

    private LiveData<List<Salidas>> listLiveData;

    public SalidasViewModel (Application application) {
        super(application);
        repository = new SalidasRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<Salidas>> getAll() { return listLiveData; }

    public void insert(Salidas salidas) { repository.insert(salidas); }

}
