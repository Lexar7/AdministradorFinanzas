package company.eduardo.administradorfinanzas.DataContext.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Presupuestos;
import company.eduardo.administradorfinanzas.DataContext.Entities.SalidasPresupuesto;
import company.eduardo.administradorfinanzas.DataContext.Repositories.SalidasPresupuestoRepository;

public class SalidasPresupuestoViewModel extends AndroidViewModel {

    private SalidasPresupuestoRepository repository;

    private LiveData<List<SalidasPresupuesto>> listLiveData;

    public SalidasPresupuestoViewModel (Application application) {
        super(application);
        repository = new SalidasPresupuestoRepository(application);
        listLiveData = repository.getAll();
    }

    public LiveData<List<SalidasPresupuesto>> getAll() { return listLiveData; }

    public LiveData<SalidasPresupuesto> getOne(int Id) { return repository.getOne(Id); }

    public void insert(SalidasPresupuesto salidasPresupuesto) { repository.insert(salidasPresupuesto); }

    public void update(SalidasPresupuesto salidasPresupuesto){ repository.update(salidasPresupuesto);}

    public void delete(SalidasPresupuesto salidasPresupuesto) {repository.delete(salidasPresupuesto);}

    public void delete(int Id) {repository.delete(Id);}
}
