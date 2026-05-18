package de.ts.btin2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insertItem(ItemEntity item);

    @Query("SELECT * FROM item WHERE item_value = :value OR item_name = :name")
    List<ItemEntity> searchItem(String name, float value);

    @Delete
    void deleteItem(ItemEntity item);

    @Query("SELECT * FROM item")
    LiveData<List<ItemEntity>> getAllItems();
}