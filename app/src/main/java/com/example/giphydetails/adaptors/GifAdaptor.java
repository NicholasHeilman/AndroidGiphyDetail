package com.example.giphydetails.adaptors;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giphydetails.R;
import com.example.giphydetails.models.GiphyResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GifAdaptor extends RecyclerView.Adapter<GifAdaptor.GiphyViewHolder> {

    private List<String> gifList;

    public GifAdaptor(){
        gifList = new ArrayList<>();
    }


    @NonNull
    @Override
    public GiphyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_gif_full, parent, false);
        return new GiphyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GiphyViewHolder holder, int position) {
        String url = gifList.get(position);

        Glide.with(holder.fullGif.getContext())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.fullGif);
    }

    @Override
    public int getItemCount() {
        return gifList.size();
    }

    public void setGifs(List<String> gifs) {
        this.gifList = gifs;
        notifyDataSetChanged();
        Log.d(TAG, "setGifs: " + gifs);
    }

    class GiphyViewHolder extends RecyclerView.ViewHolder {
        private ImageView fullGif;

        GiphyViewHolder(@NonNull View itemView) {
            super(itemView);
            fullGif = itemView.findViewById(R.id.full_gif);
        }
    }
}
