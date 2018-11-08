package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CuentasRepository;

public class CuentasViewModel extends AndroidViewModel {

    private CuentasRepository repository;

    private LiveData<List<Cuentas>> listLiveData;

    public CuentasViewModel (Application application) {
        super(application);
        repository = new CuentasRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<Cuentas>> getAll() { return listLiveData; }

    public LiveData<Cuentas> getOne(int Id) { return repository.getOne(Id); }

    public void insert(Cuentas cuentas) { repository.insert(cuentas); }

    public void update(Cuentas cuentas){ repository.update(cuentas);}

    public void delete(Cuentas cuentas) {repository.delete(cuentas);}

    public void delete(int Id) {repository.delete(Id);}
}
