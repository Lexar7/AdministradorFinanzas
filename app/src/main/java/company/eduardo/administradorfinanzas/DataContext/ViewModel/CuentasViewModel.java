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

    public void insert(Cuentas cuentas) { repository.insert(cuentas); }

}
