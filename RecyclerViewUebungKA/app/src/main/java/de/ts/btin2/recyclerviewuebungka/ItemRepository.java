package de.ts.btin2.recyclerviewuebungka;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemRepository {
    private final MutableLiveData<List<ItemEntity>> searchResults= new MutableLiveData<>();
    private List<ItemEntity> results;
    private final LiveData<List<ItemEntity>> allItems;
    private final ItemDao itemDao;

    public ItemRepository(Application application) {
        AppRoomDatabase db;
        db = AppRoomDatabase.getDatabase(application);
        itemDao = db.itemDao();
        allItems = itemDao.getAllItems();
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            searchResults.setValue(results);
        }
    };

    public void insertItem(ItemEntity newItem) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> { itemDao.insertItem(newItem);});
        executor.shutdown();
    }

    public void deleteItem(String id) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> { itemDao.deleteItem(id);});
        executor.shutdown();
    }

    public void findItem(String value) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            results = itemDao.findItem(value);
            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

    //Getter
    public LiveData<List<ItemEntity>> getAllItems() {
        return allItems;
    }
    public MutableLiveData<List<ItemEntity>> getSearchResults() {
        return searchResults;
    }
}
