package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriasCuentasRepository;

public class CategoriasCuentasViewModel extends AndroidViewModel {

    private CategoriasCuentasRepository repository;

    private LiveData<List<CategoriasCuentas>> listLiveData;

    public CategoriasCuentasViewModel (Application application) {
        super(application);
        repository = new CategoriasCuentasRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<CategoriasCuentas>> getAll() { return listLiveData; }

    public LiveData<CategoriasCuentas> getOne(int Id) { return repository.getOne(Id); }

    public void insert(CategoriasCuentas categoriasCuentas) { repository.insert(categoriasCuentas); }

    public void update(CategoriasCuentas categoriasCuentas){ repository.update(categoriasCuentas);}

    public void delete(CategoriasCuentas categoriasCuentas) {repository.delete(categoriasCuentas);}

    public void delete(int Id) {repository.delete(Id);}
}