package de.ts.btin2.blogic;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = {Vorwahl.class},version = 1,exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    private static  AppRoomDatabase roomDb =null;

    protected abstract VorwahlDao eanDao();

    static AppRoomDatabase getDatabase(final Context context) {
        if (roomDb == null) {
            synchronized (AppRoomDatabase.class) {
                if (roomDb == null) {
                    roomDb = Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, "vw_db").build();
                }
            }
        }
        return roomDb;
    }
}