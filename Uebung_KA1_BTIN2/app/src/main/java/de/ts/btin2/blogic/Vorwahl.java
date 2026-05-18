package de.ts.btin2.blogic;
import androidx.room.Entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
@Entity(tableName = "vorwahlen")
public class Vorwahl {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "vorwahl")
    private String vorwahl;

    @NonNull
    @ColumnInfo(name = "ort")
    private String ort;

    public Vorwahl(String vorwahl, String ort) {
        this.vorwahl = vorwahl;
        this.ort = ort;
    }

    public String getVorwahl() {
        return vorwahl;
    }

    public String getOrt() {
        return ort;
    }

}
