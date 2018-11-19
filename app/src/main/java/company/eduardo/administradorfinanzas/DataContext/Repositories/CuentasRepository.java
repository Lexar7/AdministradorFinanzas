package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.CuentasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.Models.InformacionGrafico;

public class CuentasRepository {

    private CuentasDao cuentasDao;
    private LiveData<List<Cuentas>> listLiveData;

    public CuentasRepository(Application application) {
        Database db = Database.getDatabase(application);
        cuentasDao = db.cuentasDao();
        listLiveData = cuentasDao.getAll();
    }

    public LiveData<List<Cuentas>> getAll() {
        return listLiveData;
    }

    public LiveData<List<InformacionGrafico>> getGraphic(int Id){
        return cuentasDao.getGraphic(Id);
    }

    public LiveData<Cuentas> getOne(int Id) {
        return cuentasDao.getOne(Id);
    }

    public void insert (Cuentas cuentas) {
        new CuentasRepository.insertAsyncTask(cuentasDao).execute(cuentas);
    }

    private static class insertAsyncTask extends AsyncTask<Cuentas, Void, Void> {

        private CuentasDao cuentasDao;

        insertAsyncTask(CuentasDao dao) {
            cuentasDao = dao;
        }

        @Override
        protected Void doInBackground(final Cuentas... params) {
            cuentasDao.insert(params[0]);
            return null;
        }
    }

    public void update(final Cuentas cuentas){
        new CuentasRepository.updateAsyncTask(cuentasDao).execute(cuentas);

    }

    private static class updateAsyncTask extends AsyncTask<Cuentas, Void, Void>{

        private CuentasDao cuentasDao;

        updateAsyncTask(CuentasDao dao) {
            cuentasDao = dao;
        }

        @Override
        protected Void doInBackground(final Cuentas... params){
            cuentasDao.update(params[0]);
            return null;
        }
    }

    public void delete(final Cuentas cuentas) {

        new  CuentasRepository.deleteAsyncTask(cuentasDao).execute(cuentas);
    }

    public void delete(final int Id) {

        final LiveData<Cuentas> data = getOne(Id);
        if (data != null) {
            new CuentasRepository.deleteAsyncTask(cuentasDao).execute(data.getValue());
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Cuentas, Void, Void>{

        private CuentasDao cuentasDao;

        deleteAsyncTask(CuentasDao dao) {
            cuentasDao = dao;
        }

        @Override
        protected Void doInBackground(final Cuentas... params){
            cuentasDao.delete(params[0]);
            return null;
        }
    }
}
