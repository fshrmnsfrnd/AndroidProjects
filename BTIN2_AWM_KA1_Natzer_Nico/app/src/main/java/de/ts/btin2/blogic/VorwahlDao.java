package de.ts.btin2.blogic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface VorwahlDao {
    @Insert
    void insert(Vorwahl vorwahl);

    @Query("DELETE FROM vorwahlen WHERE vorwahl LIKE :vorwahl AND ort LIKE :ort")
    void delete(String vorwahl, String ort);

    @Query("SELECT * FROM vorwahlen WHERE vorwahl LIKE :value OR ort LIKE :value")
    LiveData<List<Vorwahl>> find(String value);
}
