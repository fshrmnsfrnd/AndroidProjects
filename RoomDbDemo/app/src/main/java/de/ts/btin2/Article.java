package de.ts.btin2;

import androidx.room.*;
import androidx.annotation.NonNull;

@Entity(tableName = "article")
public class Article {
    //Attributes
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "quantity")
    private int quantity;

    //Constructor
    public Article(String name, int quantity){
        setName(name);
        setQuantity(quantity);
    }

    public Article(){}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    //Getters
    public int getId() {return id;}
    public String getName() {return name;}
    public int getQuantity() {return quantity;}
}
