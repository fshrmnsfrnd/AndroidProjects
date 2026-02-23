package de.ts.btin2.roomdbdemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mViewModel;
    private ArticleListAdapter adapter;

    //Views
    private EditText articleNr;
    private EditText articleName;
    private EditText articleQuantity;

    //Recycler
    RecyclerView articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind
        articleNr = findViewById(R.id.inputArticleNr);
        articleName = findViewById(R.id.inputArticleName);
        articleQuantity = findViewById(R.id.inputArticleQuantity);

        //register Observer
        //mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel = new MainViewModel(getApplication());
        mViewModel.getAllArticles().observe(this, articles -> {
            adapter.setArticleList(articles);
            System.out.println(articles.toString());
        });

        mViewModel.getSearchResults().observe(this,articles -> {
            if (articles.size() > 0) {
                articleNr.setText(String.format(Locale.GERMAN, "%d", articles.get(0).getId()));
                articleName.setText(articles.get(0).getName());
                articleQuantity.setText(String.format( Locale.GERMAN, "%d", articles.get(0).getQuantity()));
            } else {
                articleNr.setText("kein Treffer");
            }
        });

        //Build-up Recycler View
        articleList = findViewById(R.id.articleList);

        articleList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ArticleListAdapter(R.layout.article_list_item);
        articleList.setAdapter(adapter);
    }

    public void addArticle(View v) {
        String name = articleName.getText().toString();
        String quantity = articleQuantity.getText().toString();

        if (!name.equals("") && !quantity.equals("")) {
            Article article = new Article(name, Integer.parseInt(quantity));
            mViewModel.insertArticle(article);
            clearFields();
        } else {
            articleNr.setText("Unvollständige Information");
        }
    }
    public void findArticles(View view) {
        mViewModel.findArticle(articleName.getText().toString());
    }

    public void deleteArticle(View view) {
        mViewModel.deleteArticle(articleName.getText().toString());
        clearFields();
    }

    public void clearFields() {
        articleNr.setText("");
        articleName.setText("");
        articleQuantity.setText("");
    }

}