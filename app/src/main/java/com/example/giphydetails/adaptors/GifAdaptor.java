package com.example.giphydetails.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giphydetails.R;

import java.util.ArrayList;
import java.util.List;

public class GifAdaptor extends RecyclerView.Adapter<GifAdaptor.GiphyViewHolder> {

    private List<String> gifs;

    public GifAdaptor(){
        gifs = new ArrayList<>();
    }


    @NonNull
    @Override
    public GiphyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_gif_full, parent, false);
        return new GiphyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GiphyViewHolder holder, int position) {
        String url = gifs.get(position);

        Glide.with(holder.fullGif.getContext())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.fullGif);
    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    public void setUrls(List<String> gifs) {
        this.gifs = gifs;
        notifyDataSetChanged();
    }

    class GiphyViewHolder extends RecyclerView.ViewHolder {
        private ImageView fullGif;

        GiphyViewHolder(@NonNull View itemView) {
            super(itemView);
            fullGif = itemView.findViewById(R.id.full_gif);
        }
    }
}
