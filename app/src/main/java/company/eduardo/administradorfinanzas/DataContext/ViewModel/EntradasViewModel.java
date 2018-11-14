package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.Calendar;
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

    public LiveData<List<Entradas>> getAll(Calendar calendar, Calendar calendar2, int Id) { return repository.getAll(calendar, calendar2,Id); }

    public LiveData<Entradas> getOne(int Id) { return repository.getOne(Id); }

    public void insert(Entradas entrada) { repository.insert(entrada); }

    public void update(Entradas entradas){ repository.update(entradas);}

    public void delete(Entradas entradas) {repository.delete(entradas);}

    public void delete(int Id) {repository.delete(Id);}
}
