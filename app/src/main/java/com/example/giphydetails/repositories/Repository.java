package com.example.giphydetails.repositories;

import android.text.Editable;
import android.util.Log;

import com.example.giphydetails.models.GiphyResponse;
import com.example.giphydetails.retrofit.GiphyService;
import com.example.giphydetails.retrofit.RetrofitInstance;
import com.example.giphydetails.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

import static androidx.constraintlayout.widget.Constraints.TAG;

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

    public Call<GiphyResponse> getGiphys(Editable search) {
        Log.d(TAG, "getGiphys: Search = " + search);
        return retrofit.create(GiphyService.class)
                .getGiphys(
                        search,
                        Constants.API_KEY
                );
    }
}
