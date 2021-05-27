package com.trycatch.hashtagfortiktok.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={FavDataList.class},version = 1)
public abstract class FavDatabase extends RoomDatabase {

    public abstract database database();

}
