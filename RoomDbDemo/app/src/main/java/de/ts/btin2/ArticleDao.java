package de.ts.btin2;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;

@Dao
public interface ArticleDao {
    @Insert
    void insertArticle(Article article);
    @Query("SELECT * FROM article WHERE name = :name")
    List<Article> findArticle(String name);
    @Query("DELETE FROM article WHERE name = :name")
    void deleteArticle(String name);
    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAllArticles();
}
