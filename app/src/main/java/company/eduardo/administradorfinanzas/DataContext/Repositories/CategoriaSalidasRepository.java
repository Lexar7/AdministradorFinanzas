package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.CategoriaEntradasDao;
import company.eduardo.administradorfinanzas.DataContext.Dao.CategoriaSalidasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;

public class CategoriaSalidasRepository {

    private CategoriaSalidasDao categoriaSalidasDao;
    private LiveData<List<CategoriaSalidas>> listLiveData;

    public CategoriaSalidasRepository(Application application) {
        Database db = Database.getDatabase(application);
        categoriaSalidasDao = db.categoriaSalidasDao();
        listLiveData = categoriaSalidasDao.getAll();
    }

    public LiveData<List<CategoriaSalidas>> getAll() {
        return listLiveData;
    }


    public void insert (CategoriaSalidas categoriaSalidas) {
        new CategoriaSalidasRepository.insertAsyncTask(categoriaSalidasDao).execute(categoriaSalidas);
    }

    private static class insertAsyncTask extends AsyncTask<CategoriaSalidas, Void, Void> {

        private CategoriaSalidasDao salidasDao;

        insertAsyncTask(CategoriaSalidasDao dao) {
            salidasDao = dao;
        }

        @Override
        protected Void doInBackground(final CategoriaSalidas... params) {
            salidasDao.insert(params[0]);
            return null;
        }
    }

    public void update(final CategoriaSalidas categoriaSalidas){
        new CategoriaSalidasRepository.updateAsyncTask(categoriaSalidasDao).execute(categoriaSalidas);

    }

    private static class updateAsyncTask extends AsyncTask<CategoriaSalidas, Void, Void>{

        private CategoriaSalidasDao salidasDaoo;

        updateAsyncTask(CategoriaSalidasDao dao) {
            salidasDaoo = dao;
        }

        @Override
        protected Void doInBackground(final CategoriaSalidas... params){
            salidasDaoo.update(params[0]);
            return null;
        }
    }
}