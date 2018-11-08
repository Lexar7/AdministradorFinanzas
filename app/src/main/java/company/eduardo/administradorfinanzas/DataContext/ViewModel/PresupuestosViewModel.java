package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Presupuestos;
import company.eduardo.administradorfinanzas.DataContext.Repositories.PresupuestosRepository;

public class PresupuestosViewModel extends AndroidViewModel {

    private PresupuestosRepository repository;

    private LiveData<List<Presupuestos>> listLiveData;

    public PresupuestosViewModel (Application application) {
        super(application);
        repository = new PresupuestosRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<Presupuestos>> getAll() { return listLiveData; }

    public LiveData<Presupuestos> getOne(int Id) { return repository.getOne(Id); }

    public void insert(Presupuestos presupuestos) { repository.insert(presupuestos); }

    public void update(Presupuestos presupuestos){ repository.update(presupuestos);}

    public void delete(Presupuestos presupuestos) {repository.delete(presupuestos);}

    public void delete(int Id) {repository.delete(Id);}
}
