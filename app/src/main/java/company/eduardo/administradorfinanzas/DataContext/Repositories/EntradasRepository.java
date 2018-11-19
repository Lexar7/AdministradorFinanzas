package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.Calendar;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.EntradasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.Models.InformacionGrafico;

public class EntradasRepository {

    private EntradasDao entradasDao;
    private LiveData<List<Entradas>> listLiveData;

    public EntradasRepository(Application application) {
        Database db = Database.getDatabase(application);
        entradasDao = db.entradasDao();
        listLiveData = entradasDao.getAll();
    }

    public LiveData<List<Entradas>> getAll() {
        return listLiveData;
    }

    public LiveData<List<Entradas>> getAll(Calendar calendar) {
        return entradasDao.getAll(calendar);
    }

    public LiveData<List<Entradas>> getAll(Calendar calendar, Calendar calendar2) {
        return entradasDao.getAll(calendar, calendar2);
    }

    public LiveData<List<Entradas>> getAll(Calendar calendar, Calendar calendar2, int Id) {
        return entradasDao.getAll(calendar, calendar2, Id);
    }

    public LiveData<List<InformacionGrafico>> getGraphic(int Id){
        return entradasDao.getGraphic(Id);
    }

    public LiveData<Entradas> getOne(int Id) {
        return entradasDao.getOne(Id);
    }

    public void insert (Entradas entradas) {
        new EntradasRepository.insertAsyncTask(entradasDao).execute(entradas);
    }

    private static class insertAsyncTask extends AsyncTask<Entradas, Void, Void> {

        private EntradasDao cuentasDao;

        insertAsyncTask(EntradasDao dao) {
            cuentasDao = dao;
        }

        @Override
        protected Void doInBackground(final Entradas... params) {
            cuentasDao.insert(params[0]);
            return null;
        }
    }

    public void update(final Entradas entradas){
        new EntradasRepository.updateAsyncTask(entradasDao).execute(entradas);

    }

    private static class updateAsyncTask extends AsyncTask<Entradas, Void, Void>{

        private EntradasDao entradasDao;

        updateAsyncTask(EntradasDao dao) {
            entradasDao = dao;
        }

        @Override
        protected Void doInBackground(final Entradas... params){
            entradasDao.update(params[0]);
            return null;
        }
    }

    public void delete(final Entradas entradas) {

        new  EntradasRepository.deleteAsyncTask(entradasDao).execute(entradas);
    }

    public void delete(final int Id) {

        final LiveData<Entradas> entradas = getOne(Id);
        if (entradas != null) {
            new EntradasRepository.deleteAsyncTask(entradasDao).execute(entradas.getValue());
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Entradas, Void, Void>{

        private EntradasDao entradasDao;

        deleteAsyncTask(EntradasDao dao) {
            entradasDao = dao;
        }

        @Override
        protected Void doInBackground(final Entradas... params){
            entradasDao.delete(params[0]);
            return null;
        }
    }
}
