package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.PresupuestosDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.Presupuestos;

public class PresupuestosRepository {

    private PresupuestosDao presupuestosDao;
    private LiveData<List<Presupuestos>> listLiveData;

    public PresupuestosRepository(Application application) {
        Database db = Database.getDatabase(application);
        presupuestosDao = db.presupuestosDao();
        listLiveData = presupuestosDao.getAll();
    }

    public LiveData<List<Presupuestos>> getAll() {
        return listLiveData;
    }


    public void insert (Presupuestos presupuestos) {
        new PresupuestosRepository.insertAsyncTask(presupuestosDao).execute(presupuestos);
    }

    private static class insertAsyncTask extends AsyncTask<Presupuestos, Void, Void> {

        private PresupuestosDao presupuestosDao1;

        insertAsyncTask(PresupuestosDao dao) {
            presupuestosDao1 = dao;
        }

        @Override
        protected Void doInBackground(final Presupuestos... params) {
            presupuestosDao1.insert(params[0]);
            return null;
        }
    }

    public void update(final Presupuestos presupuestos){
        new PresupuestosRepository.updateAsyncTask(presupuestosDao).execute(presupuestos);

    }

    private static class updateAsyncTask extends AsyncTask<Presupuestos, Void, Void>{

        private PresupuestosDao presupuestosDao;

        updateAsyncTask(PresupuestosDao dao) {
            presupuestosDao = dao;
        }

        @Override
        protected Void doInBackground(final Presupuestos... params){
            presupuestosDao.update(params[0]);
            return null;
        }
    }
}
