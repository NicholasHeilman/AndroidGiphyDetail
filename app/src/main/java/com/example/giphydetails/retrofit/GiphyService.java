package com.example.giphydetails.retrofit;

import android.text.Editable;

import com.example.giphydetails.models.GiphyResponse;
import com.example.giphydetails.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiphyService {

        @GET(Constants.GIF_PATH)
        Call<GiphyResponse> getGiphys(
                @Query("q") Editable search,
                @Query("api_key") String API_KEY

        );
    }

