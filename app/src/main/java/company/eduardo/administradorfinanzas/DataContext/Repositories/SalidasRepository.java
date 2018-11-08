package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.SalidasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;

public class SalidasRepository {

    private SalidasDao salidasDao;
    private LiveData<List<Salidas>> listLiveData;

    public SalidasRepository(Application application) {
        Database db = Database.getDatabase(application);
        salidasDao = db.salidasDao();
        listLiveData = salidasDao.getAll();
    }

    public LiveData<List<Salidas>> getAll() {
        return listLiveData;
    }


    public void insert (Salidas salidas) {
        new SalidasRepository.insertAsyncTask(salidasDao).execute(salidas);
    }

    private static class insertAsyncTask extends AsyncTask<Salidas, Void, Void> {

        private SalidasDao salidasDao1;

        insertAsyncTask(SalidasDao dao) {
            salidasDao1 = dao;
        }

        @Override
        protected Void doInBackground(final Salidas... params) {
            salidasDao1.insert(params[0]);
            return null;
        }
    }

    public void update(final Salidas salidas){
        new SalidasRepository.updateAsyncTask(salidasDao).execute(salidas);

    }

    private static class updateAsyncTask extends AsyncTask<Salidas, Void, Void>{

        private SalidasDao salidasDao;

        updateAsyncTask(SalidasDao dao) {
            salidasDao = dao;
        }

        @Override
        protected Void doInBackground(final Salidas... params){
            salidasDao.update(params[0]);
            return null;
        }
    }
}
