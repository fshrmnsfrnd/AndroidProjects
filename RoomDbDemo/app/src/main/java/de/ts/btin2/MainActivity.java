package de.ts.btin2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.ts.btin2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mViewModel;
    private ArticleListAdapter adapter;
    private EditText articleNr;
    private EditText articleName;
    private EditText articleQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        articleNr = findViewById(R.id.inputArticleNr);
        articleName = findViewById(R.id.inputArticleName);
        articleQuantity = findViewById(R.id.inputArticleQuantity);

        mViewModel = new MainViewModel(getApplication());

        mViewModel.getSearchResults().observe(this, articles -> {
            if (!articles.isEmpty()){
                articleNr.setText(String.valueOf(articles.get(0).getId()));
                articleName.setText(articles.get(0).getName());
                articleQuantity.setText(String.valueOf(articles.get(0).getQuantity()));
            } else {
                articleNr.setText("kein Treffer");
            }
        });
        RecyclerView articleList = findViewById(R.id.articleList);
        articleList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ArticleListAdapter(R.layout.article_list_item, binding);
        articleList.setAdapter(adapter);
        mViewModel.getAllArticles().observe(this, articles -> {
            adapter.setArticleList(articles);
        });
    }

    public void clearFields() {
        articleNr.setText("");
        articleName.setText("");
        articleQuantity.setText("");
    }

    public void addArticle(View v) {
        String name = articleName.getText().toString();
        String quantity = articleQuantity.getText().toString();
        Log.d("my","-------"+ name + quantity);
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

}