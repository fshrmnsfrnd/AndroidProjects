package de.ts.alarms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface AlarmDao {
    @Insert
    void insertAlarm(Alarm alarm);

    @Update
    void updateAlarm(Alarm alarm);

    @Query("DELETE FROM tbl_alarms WHERE alarm_id = :id")
    void deleteAlarm(int id);

    @Query("SELECT * FROM tbl_alarms")
    LiveData<List<Alarm>> getAllAlarms();
}
