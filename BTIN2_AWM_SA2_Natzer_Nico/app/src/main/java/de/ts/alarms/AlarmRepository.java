package de.ts.alarms;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import androidx.lifecycle.LiveData;


public class AlarmRepository {

    private final LiveData<List<Alarm>> allAlarms;
    private final AlarmDao alarmDao;


    public AlarmRepository(Application application)
    {
        AppRoomDatabase db;
        db = AppRoomDatabase.getDatabase(application);
        alarmDao = db.alarmDao();
        allAlarms = alarmDao.getAllAlarms();
    }

    public void insertAlarm(Alarm newAlarm)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {alarmDao.insertAlarm(newAlarm);});
        executor.shutdown();
    }
    public void updateAlarm(Alarm newAlarm)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {alarmDao.updateAlarm(newAlarm);});
        executor.shutdown();
    }
    public void deleteAlarm(int alarmId) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> { alarmDao.deleteAlarm(alarmId);});
        executor.shutdown();
    }


    //Getter
    public LiveData<List<Alarm>> getAllAlarms()
    {
        return allAlarms;
    }

}

