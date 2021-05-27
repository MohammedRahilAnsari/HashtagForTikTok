package com.trycatch.hashtagfortiktok.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "favlist", indices = {@Index(value = {"chiptext"},
        unique = true)})
public class FavDataList {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "chiptext")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}