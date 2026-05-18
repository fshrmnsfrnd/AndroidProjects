package de.ts.alarms;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Alarm.class}, version = 1,exportSchema=false)
public abstract class AppRoomDatabase extends RoomDatabase {
    private static  AppRoomDatabase roomDb =null;

    public abstract AlarmDao alarmDao();

    static AppRoomDatabase getDatabase(final Context context) {
        if (roomDb == null) {
            synchronized (AppRoomDatabase.class) {
                if (roomDb == null) {
                    roomDb = Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, "alarm_database").build();
                }
            }
        }
        return roomDb;
    }
}
