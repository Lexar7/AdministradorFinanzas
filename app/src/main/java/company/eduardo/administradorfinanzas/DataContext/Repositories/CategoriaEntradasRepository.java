package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.CategoriaEntradasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;

public class CategoriaEntradasRepository {

    private CategoriaEntradasDao categoriaEntradasDao;
    private LiveData<List<CategoriaEntradas>> listLiveData;

    public CategoriaEntradasRepository(Application application) {
        Database db = Database.getDatabase(application);
        categoriaEntradasDao = db.categoriaEntradasDao();
        listLiveData = categoriaEntradasDao.getAll();
    }

    public LiveData<List<CategoriaEntradas>> getAll() {
        return listLiveData;
    }

    public void insert (CategoriaEntradas categoriaEntradas) {
        new CategoriaEntradasRepository.insertAsyncTask(categoriaEntradasDao).execute(categoriaEntradas);
    }


    private static class insertAsyncTask extends AsyncTask<CategoriaEntradas, Void, Void> {

        private CategoriaEntradasDao entradasDao;

        insertAsyncTask(CategoriaEntradasDao dao) {
            entradasDao = dao;
        }

        @Override
        protected Void doInBackground(final CategoriaEntradas... params) {
            entradasDao.insert(params[0]);
            return null;
        }
    }

    public void update(final CategoriaEntradas categoriaEntradas){
         new CategoriaEntradasRepository.updateAsyncTask(categoriaEntradasDao).execute(categoriaEntradas);

    }

    private static class updateAsyncTask extends AsyncTask<CategoriaEntradas, Void, Void>{

        private CategoriaEntradasDao entradasDao;

        updateAsyncTask(CategoriaEntradasDao dao) {
            entradasDao = dao;
        }

        @Override
        protected Void doInBackground(final CategoriaEntradas... params){
            entradasDao.update(params[0]);
            return null;
        }
    }
}
