package com.trycatch.hashtagfortiktok.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trycatch.hashtagfortiktok.Adapter.CatergoryAdapter;
import com.trycatch.hashtagfortiktok.ApiClient.ApiClient;
import com.trycatch.hashtagfortiktok.Model.Example;
import com.trycatch.hashtagfortiktok.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_List extends AppCompatActivity {

    Context context;
    private List<Example> exampleList;
    private RecyclerView recyclerView;

    protected View onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__list);
        getCategoryData();
        context = container.getContext();

        View view = inflater.inflate(R.layout.activity_second_navigator, container, false);
        return view;
    }

    private void getCategoryData() {
        Call<List<Example>> call = ApiClient.getApiService().getalldata();

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {

                if (response.body() != null) {
                    exampleList = response.body();
                    recyclerView = findViewById(R.id.rv);
                    CatergoryAdapter catergoryAdapter = new CatergoryAdapter(Category_List.this, exampleList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(Category_List.this, 4);
                    gridLayoutManager.setOrientation(gridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(catergoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Toast.makeText(Category_List.this, "Something went Wrong Please Try Again After Sometimes", Toast.LENGTH_SHORT).show();
            }
        });
    }
}