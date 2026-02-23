package de.ts.btin2.roomdbdemo2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class MainViewModel extends AndroidViewModel{
    private ArticleRepository repository;
    private LiveData<List<Article>> allArticles;
    private MutableLiveData<List<Article>> searchResults;

    public MainViewModel (Application application) {
        super(application);
        repository = new ArticleRepository(application);
        allArticles = repository.getAllArticles();
        searchResults = repository.getSearchResults();
    }
    MutableLiveData<List<Article>> getSearchResults() {
        return searchResults;
    }
    LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }
    public void insertArticle(Article article) {
        repository.insertArticle(article);
    }
    public void findArticle(String name) {
        repository.findArticle(name);
    }
    public void deleteArticle(String name) {
        repository.deleteArticle(name);
    }
}