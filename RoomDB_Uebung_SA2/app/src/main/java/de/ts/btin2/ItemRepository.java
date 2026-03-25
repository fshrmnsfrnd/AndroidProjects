package de.ts.btin2;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
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
        public void handleMessage(@NonNull Message msg) {
            searchResults.setValue(results);
        }
    };

    public void insertItem(ItemEntity newItem) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {itemDao.insertItem(newItem);});
        executor.shutdown();
    }

    public void deleteItem(ItemEntity item) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {itemDao.deleteItem(item);});
        executor.shutdown();
    }

    public void searchItem(String name, float value) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            results = itemDao.searchItem(name, value);
            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

    public LiveData<List<ItemEntity>> getAllItems() {return allItems;}
    public MutableLiveData<List<ItemEntity>> getSearchResults() {return searchResults;}
}