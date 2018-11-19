package company.eduardo.administradorfinanzas.DataContext.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.Calendar;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.SalidasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.Models.InformacionGrafico;

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

    public LiveData<List<Salidas>> getAll(Calendar calendar) {
        return salidasDao.getAll(calendar);
    }

    public LiveData<List<Salidas>> getAll(Calendar calendar, Calendar calendar2) {
        return salidasDao.getAll(calendar, calendar2);
    }

    public LiveData<List<Salidas>> getAll(Calendar calendar, Calendar calendar2, int Id) {
        return salidasDao.getAll(calendar, calendar2, Id);
    }

    public LiveData<List<InformacionGrafico>> getGraphic(int Id){
        return salidasDao.getGraphic(Id);
    }

    public LiveData<Salidas> getOne(int Id) {
        return salidasDao.getOne(Id);
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

    public void delete(final Salidas salidas) {

        new  SalidasRepository.deleteAsyncTask(salidasDao).execute(salidas);
    }

    public void delete(final int Id) {

        final LiveData<Salidas> data = getOne(Id);
        if (data != null) {
            new SalidasRepository.deleteAsyncTask(salidasDao).execute(data.getValue());
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Salidas, Void, Void>{

        private SalidasDao salidasDao;

        deleteAsyncTask(SalidasDao dao) {
            salidasDao = dao;
        }

        @Override
        protected Void doInBackground(final Salidas... params){
            salidasDao.delete(params[0]);
            return null;
        }
    }
}
