package com.trycatch.hashtagfortiktok.ApiService;

import com.trycatch.hashtagfortiktok.Model.DataResponses;
import com.trycatch.hashtagfortiktok.Model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("tiktok_hashtag_category_list")
    Call<List<Example>> getalldata();

    @GET("tiktok_hashtag_post_list")
    Call<List<DataResponses>> getalldata(@Query("category_id") String category_id);

}