package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.CategoriasCuentasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;

public class CategoriasCuentasRepository {

    private CategoriasCuentasDao categoriasCuentasDao;
    private LiveData<List<CategoriasCuentas>> listLiveData;

    public CategoriasCuentasRepository(Application application) {
        Database db = Database.getDatabase(application);
        categoriasCuentasDao = db.categoriasCuentasDao();
        listLiveData = categoriasCuentasDao.getAll();
    }

    public LiveData<List<CategoriasCuentas>> getAll() {
        return listLiveData;
    }

    public LiveData<CategoriasCuentas> getOne(int Id) {
        return categoriasCuentasDao.getOne(Id);
    }

    public void insert (CategoriasCuentas categoriasCuentas) {
        new insertAsyncTask(categoriasCuentasDao).execute(categoriasCuentas);
    }

    private static class insertAsyncTask extends AsyncTask<CategoriasCuentas, Void, Void> {

        private CategoriasCuentasDao cuentasDao;

        insertAsyncTask(CategoriasCuentasDao dao) {
            cuentasDao = dao;
        }

        @Override
        protected Void doInBackground(final CategoriasCuentas... params) {
            cuentasDao.insert(params[0]);
            return null;
        }
    }

    public void update(final CategoriasCuentas categoriasCuentas){
        new CategoriasCuentasRepository.updateAsyncTask(categoriasCuentasDao).execute(categoriasCuentas);

    }

    private static class updateAsyncTask extends AsyncTask<CategoriasCuentas, Void, Void>{

        private CategoriasCuentasDao categoriasCuentasDao;

        updateAsyncTask(CategoriasCuentasDao dao) {
            categoriasCuentasDao = dao;
        }

        @Override
        protected Void doInBackground(final CategoriasCuentas... params){
            categoriasCuentasDao.update(params[0]);
            return null;
        }
    }

    public void delete(final CategoriasCuentas categoriasCuentas) {

        new  CategoriasCuentasRepository.deleteAsyncTask(categoriasCuentasDao).execute(categoriasCuentas);
    }

    public void delete(final int Id) {

        final LiveData<CategoriasCuentas> data = getOne(Id);
        if (data != null) {
            new CategoriasCuentasRepository.deleteAsyncTask(categoriasCuentasDao).execute(data.getValue());
        }
    }

    private static class deleteAsyncTask extends AsyncTask<CategoriasCuentas, Void, Void>{

        private CategoriasCuentasDao cuentasDao;

        deleteAsyncTask(CategoriasCuentasDao dao) {
            cuentasDao = dao;
        }

        @Override
        protected Void doInBackground(final CategoriasCuentas... params){
            cuentasDao.delete(params[0]);
            return null;
        }
    }
}
