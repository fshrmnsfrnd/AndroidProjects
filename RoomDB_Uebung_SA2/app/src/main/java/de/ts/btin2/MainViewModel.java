package de.ts.btin2;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ItemRepository repository;
    private LiveData<List<ItemEntity>> allItems;
    private MutableLiveData<List<ItemEntity>> searchResults;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
        searchResults = repository.getSearchResults();
    }

    public MutableLiveData<List<ItemEntity>> getSearchResults() {return searchResults;}

    public LiveData<List<ItemEntity>> getAllItems() {return allItems;}

    public void insertItem(ItemEntity item) {repository.insertItem(item);}

    public void searchItem(String name, float value) {repository.searchItem(name, value);}

    public void deleteItem(ItemEntity item) {repository.deleteItem(item);}
}