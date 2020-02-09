package com.example.giphydetails.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "https://api.giphy.com";

    private RetrofitInstance() {
    }

    private static class RetrofitInstanceHolder {
        private static final Retrofit INSTANCE =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
    }

    public static Retrofit getInstance() {
        return RetrofitInstanceHolder.INSTANCE;
    }
}
