package com.example.giphydetails.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GiphyService {

     String GIF_PATH = "/v1/gifs/search";

        @GET( GIF_PATH)
        Call<List<String>> getGiphys(
                @Query("Search") String search

        );
    }

