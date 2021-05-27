package com.trycatch.hashtagfortiktok.ApiClient;

import com.trycatch.hashtagfortiktok.ApiService.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private  static  final  String BASE_URL = "http://mapi.trycatchtech.com/v1/tiktok_hashtag/";
    public  static ApiService getApiService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}