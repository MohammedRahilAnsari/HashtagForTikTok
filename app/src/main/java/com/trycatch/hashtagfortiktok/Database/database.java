package com.trycatch.hashtagfortiktok.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface database {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addData(FavDataList favlist);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addData(List<FavDataList> favlist);

    @Query("select * from favlist")
    public List<FavDataList> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favlist WHERE id=:id)")
    public int isFavorite(int id);

    @Delete
    public void delete(FavDataList favlist);


}