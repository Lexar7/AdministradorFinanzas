package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.CuentasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;

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
}
