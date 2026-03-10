package de.ts.btin2.recyclerviewuebungka;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insertItem(ItemEntity item);
    @Query("SELECT * FROM item WHERE item_value = :value")
    List<ItemEntity> findItem(String value);

    @Query("DELETE FROM item WHERE item_id = :id")
    void deleteItem(String id);

    @Query("SELECT * FROM item")
    LiveData<List<ItemEntity>> getAllItems();
}
