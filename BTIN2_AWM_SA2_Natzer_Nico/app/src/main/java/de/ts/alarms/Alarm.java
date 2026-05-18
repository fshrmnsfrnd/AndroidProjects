package de.ts.alarms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "tbl_alarms")                           // 1P
public class Alarm {
    @PrimaryKey(autoGenerate = true)                        // 1.5P
    @NonNull
    @ColumnInfo(name = "alarm_id")
    private int id = 0;

    @NonNull
    @ColumnInfo(name = "alarm_ort")
    private String ort = "";

    @NonNull
    @ColumnInfo(name = "alarm_zeit")
    private String zeit = "";

    public Alarm(){
        this.id = 0;
        this.ort = "kein Ort";

        //Default heute und jetzt
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        this.zeit = sdf.format(now);


    }
    @Ignore
    public Alarm(@NonNull int id, @NonNull  String ort,  @NonNull String zeit) {
        this.id = id;
        this.ort = ort;

        if (zeit == null){
            //Default jetzt
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            zeit = sdf.format(now);
        }
        this.zeit = zeit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(@NonNull String ort) {
        this.ort = ort;
    }

    @NonNull
    public String getZeit() {
        return zeit;
    }

    public void setZeit(@NonNull String zeit) {
        this.zeit = zeit;
    }
}
