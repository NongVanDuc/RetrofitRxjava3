package com.bob.democonstrain.retrofit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static Retrofit ourInstance;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getInstance() {
        if (ourInstance == null)
            ourInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        return ourInstance;
    }

    private RetrofitClient() {
    }
}
