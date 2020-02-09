package com.example.giphydetails.viewmodels;

import android.text.Editable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.giphydetails.models.GiphyResponse;
import com.example.giphydetails.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainViewModel extends ViewModel {

    private MutableLiveData<GiphyResponse> mGifs = new MutableLiveData<GiphyResponse>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    private Repository repo = Repository.getInstance();

    public void fetchGifData(Editable search){
        repo.getGiphys(search)
                .enqueue(new Callback<GiphyResponse>() {
                    @Override
                    public void onResponse(Call<GiphyResponse> call, Response<GiphyResponse> gifResponse) {
                        mGifs.postValue(gifResponse.body());
                        Log.d(TAG, "onResponse: "+ gifResponse);
                        error.postValue("");
                    }

                    @Override
                    public void onFailure(Call<GiphyResponse> call, Throwable t) {
//                        mGifs.postValue(call);
                        Log.d(TAG, "onFailure:" );
                    }
                });
    }

    public LiveData<GiphyResponse> fetchGifData(){
        return mGifs;
    }

    public LiveData<String> getErrorLiveData() {
        return error;
    }
}
