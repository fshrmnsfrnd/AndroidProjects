package de.ts.btin2;

import androidx.room.Entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity(tableName = "item")
public class ItemEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "item_id")
    private int id;

    @ColumnInfo(name = "item_name")
    private String name;

    @ColumnInfo(name = "item_value")
    private float value;

    public ItemEntity(String name,float value) {this.name = name;this.value = value;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getValue() {
        return value;
    }
    public void setValue(float value) {
        this.value = value;
    }
}
