package de.ts.btin2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleItem> {
    private final int articleItemLayout;
    private List<Article> articles;
    private ViewBinding binding;
    public ArticleListAdapter(int layoutId, ViewBinding binding) {
        articleItemLayout = layoutId;
    }

    public void setArticleList(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    @NonNull
    @Override
    public ArticleItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(articleItemLayout, parent, false);
        return new ArticleItem(view);
    }

    @Override
    public void onBindViewHolder(final ArticleItem item, final int listPosition) {
        Article article = articles.get(listPosition);
        item.name.setText(article.getName());
        item.quantity.setText("Anzahl: " + String.valueOf(article.getQuantity()));
    }

    static class ArticleItem extends RecyclerView.ViewHolder {
        TextView name;
        TextView quantity;
        ArticleItem(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.articleItemName);
            quantity = itemView.findViewById(R.id.articleItemQuantity);
            Log.d("article", String.format("Created Article Item using: %s with %s", name, quantity.toString()));
        }
    }
}

