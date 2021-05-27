package com.trycatch.hashtagfortiktok.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.trycatch.hashtagfortiktok.Database.FavDatabase;
import com.trycatch.hashtagfortiktok.R;

public class Fragment_Fav extends androidx.fragment.app.Fragment {

    public static FavDatabase favDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fav_screen, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favDatabase = Room.databaseBuilder(getContext(), FavDatabase.class, "myfavdb").allowMainThreadQueries().build();

        Toast.makeText(getContext(), String.valueOf(favDatabase.database().getFavoriteData().size()), Toast.LENGTH_SHORT).show();

        /*debug toast*/

        Toast.makeText(getContext(), "1234", Toast.LENGTH_SHORT).show();
    }
}