package com.example.giphydetails.repositories;

import com.example.giphydetails.retrofit.GiphyService;
import com.example.giphydetails.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Repository {

    private Retrofit retrofit;

    private Repository() {
        retrofit = RetrofitInstance.getInstance();
    }

    private static class RepositoryHolder {
        private static final Repository INSTANCE = new Repository();
    }

    public static Repository getInstance() {
        return RepositoryHolder.INSTANCE;
    }

    public Call<List<String>> getGiphys(String search) {
        return retrofit.create(GiphyService.class)
                .getGiphys(
                        String.valueOf(search)
                );
    }
}
