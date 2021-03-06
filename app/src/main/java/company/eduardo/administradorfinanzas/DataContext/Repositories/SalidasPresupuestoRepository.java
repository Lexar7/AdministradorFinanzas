package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.SalidasPresupuestoDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.SalidasPresupuesto;

public class SalidasPresupuestoRepository {

    private SalidasPresupuestoDao salidasPresupuestoDao;
    private LiveData<List<SalidasPresupuesto>> listLiveData;

    public SalidasPresupuestoRepository(Application application) {
        Database db = Database.getDatabase(application);
        salidasPresupuestoDao = db.salidasPresupuestoDao();
        listLiveData = salidasPresupuestoDao.getAll();
    }

    public LiveData<List<SalidasPresupuesto>> getAll() {
        return listLiveData;
    }

    public LiveData<SalidasPresupuesto> getOne(int Id) {
        return salidasPresupuestoDao.getOne(Id);
    }

    public void insert (SalidasPresupuesto salidasPresupuesto) {
        new SalidasPresupuestoRepository.insertAsyncTask(salidasPresupuestoDao).execute(salidasPresupuesto);
    }

    private static class insertAsyncTask extends AsyncTask<SalidasPresupuesto, Void, Void> {

        private SalidasPresupuestoDao salidasPresupuestoDao1;

        insertAsyncTask(SalidasPresupuestoDao dao) {
            salidasPresupuestoDao1 = dao;
        }

        @Override
        protected Void doInBackground(final SalidasPresupuesto... params) {
            salidasPresupuestoDao1.insert(params[0]);
            return null;
        }
    }

    public void update(final SalidasPresupuesto salidasPresupuesto){
        new SalidasPresupuestoRepository.updateAsyncTask(salidasPresupuestoDao).execute(salidasPresupuesto);

    }

    private static class updateAsyncTask extends AsyncTask<SalidasPresupuesto, Void, Void>{

        private SalidasPresupuestoDao salidasPresupuestoDao;

        updateAsyncTask(SalidasPresupuestoDao dao) {
            salidasPresupuestoDao = dao;
        }

        @Override
        protected Void doInBackground(final SalidasPresupuesto... params){
            salidasPresupuestoDao.update( params[0]);
            return null;
        }
    }

    public void delete(final SalidasPresupuesto salidasPresupuesto) {

        new  SalidasPresupuestoRepository.deleteAsyncTask(salidasPresupuestoDao).execute(salidasPresupuesto);
    }

    public void delete(final int Id) {

        final LiveData<SalidasPresupuesto> data = getOne(Id);
        if (data != null) {
            new SalidasPresupuestoRepository.deleteAsyncTask(salidasPresupuestoDao).execute(data.getValue());
        }
    }

    private static class deleteAsyncTask extends AsyncTask<SalidasPresupuesto, Void, Void>{

        private SalidasPresupuestoDao salidasPresupuestoDao;

        deleteAsyncTask(SalidasPresupuestoDao dao) {
            salidasPresupuestoDao = dao;
        }

        @Override
        protected Void doInBackground(final SalidasPresupuesto... params){
            salidasPresupuestoDao.delete(params[0]);
            return null;
        }
    }
}
