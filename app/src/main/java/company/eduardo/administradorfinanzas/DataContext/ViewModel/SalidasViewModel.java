package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.SalidasRepository;
import company.eduardo.administradorfinanzas.Models.InformacionGrafico;

public class SalidasViewModel extends AndroidViewModel {

    private SalidasRepository repository;

    private LiveData<List<Salidas>> listLiveData;

    public SalidasViewModel (Application application) {
        super(application);
        repository = new SalidasRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<Salidas>> getAll() { return listLiveData; }

    public LiveData<List<Salidas>> getAll(Calendar calendar, Calendar calendar2) { return repository.getAll(calendar, calendar2); }

    public LiveData<List<Salidas>> getAll(Calendar calendar) { return repository.getAll(calendar); }

    public LiveData<List<Salidas>> getAll(Calendar calendar, Calendar calendar2, int Id) { return repository.getAll(calendar, calendar2,Id); }

    public LiveData<List<InformacionGrafico>> getGraphic(int Id){
        return repository.getGraphic(Id);
    }

    public LiveData<Salidas> getOne(int Id) { return repository.getOne(Id); }

    public void insert(Salidas salidas) { repository.insert(salidas); }

    public void update(Salidas salidas){ repository.update(salidas);}

    public void delete(Salidas salidas) {repository.delete(salidas);}

    public void delete(int Id) {repository.delete(Id);}
}
