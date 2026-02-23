package de.ts.btin2.roomdbdemo2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article")
public class Article {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "article_id")
    private int id;
    @ColumnInfo(name = "article_name")
    private String name;
    @ColumnInfo(name = "article_quantity")
    private int quantity;

    public Article(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}