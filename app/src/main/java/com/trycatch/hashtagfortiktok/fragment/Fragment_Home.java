package com.trycatch.hashtagfortiktok.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trycatch.hashtagfortiktok.Adapter.CatergoryAdapter;
import com.trycatch.hashtagfortiktok.ApiClient.ApiClient;
import com.trycatch.hashtagfortiktok.Model.Example;
import com.trycatch.hashtagfortiktok.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Home extends androidx.fragment.app.Fragment {

    Context context;

    private List<Example> exampleList;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        getCategoryData();

        View view = inflater.inflate(R.layout.activity_category__list, container, false);
        recyclerView = view.findViewById(R.id.rv);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        return view;
    }

    private void getCategoryData() {
        Call<List<Example>> call = ApiClient.getApiService().getalldata();

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {

                if (response.body() != null) {
                    exampleList = response.body();
                    CatergoryAdapter catergoryAdapter = new CatergoryAdapter(context, exampleList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
                    gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(catergoryAdapter);
                }

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Toast.makeText(context, "Something went Wrong Please Try Again After Sometimes", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

}