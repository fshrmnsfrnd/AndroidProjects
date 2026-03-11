package de.ts.btin2.blogic;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class VorwahlRepository {
    private final MutableLiveData<List<Vorwahl>> vorwahlen = new MutableLiveData<>();
    private List<Vorwahl> results;
    private final VorwahlDao vwDao;


    Handler handler = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg)
        {
            vorwahlen.setValue(results);
        }
    };

    public VorwahlRepository(Application application)
    {
        AppRoomDatabase db;
        db = AppRoomDatabase.getDatabase(application);
        vwDao = db.eanDao();

    }

    public void insert(Vorwahl vorwahl)
    {ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
        vwDao.insert(vorwahl);});
        executor.shutdown();
    }

    public void delete(Vorwahl vorwahl) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {vwDao.delete(vorwahl.getVorwahl(), vorwahl.getOrt());});
        executor.shutdown();
    }

    public void find(String suchbegriff)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            results = vwDao.find(suchbegriff);
            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }




    public MutableLiveData<List<Vorwahl>> getResults()
    {
        return vorwahlen;
    }

}
