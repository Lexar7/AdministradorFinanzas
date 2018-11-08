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

    public LiveData<Salidas> getOne(int Id) { return repository.getOne(Id); }

    public void insert(Salidas salidas) { repository.insert(salidas); }

    public void update(Salidas salidas){ repository.update(salidas);}

    public void delete(Salidas salidas) {repository.delete(salidas);}

    public void delete(int Id) {repository.delete(Id);}
}
