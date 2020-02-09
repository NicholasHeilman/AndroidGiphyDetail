package com.example.giphydetails;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.giphydetails.adaptors.GifAdaptor;
import com.example.giphydetails.models.DataItem;
import com.example.giphydetails.models.GiphyResponse;
import com.example.giphydetails.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class GifListFragment extends Fragment {

//    private MainViewModel viewModel
    private Button btnSearch;
    private Button btnToggleView;
    private EditText etSearch;
    private GifAdaptor adaptor;
    private Editable search;
    private ImageView ivTest;
    private MainViewModel mainViewModel;
    private String Editable;
    private ArrayList<String> gifList;
    String item;

String test;

    public GifListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gif_list, container, false);
        mainViewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);


        RecyclerView rvGifs = view.findViewById(R.id.rvGifs);
        rvGifs.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptor = new GifAdaptor();
        rvGifs.setAdapter(adaptor);
//        rvGifs.setHasFixedSize(true);
        ivTest = view.findViewById(R.id.ivTest);
        btnSearch = view.findViewById(R.id.btnSearch);
        etSearch = view.findViewById(R.id.etSearch);
        btnToggleView = view.findViewById(R.id.btnToggleView);
        setupObservers();
        gifList = new ArrayList<>();

        btnToggleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"Toggle View", Toast.LENGTH_SHORT).show();


                if (rvGifs.getLayoutManager() instanceof GridLayoutManager) {
                    rvGifs.setLayoutManager(new LinearLayoutManager(getContext()));
                    btnToggleView.setText("Grid");
                } else {
                    rvGifs.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    btnToggleView.setText("List");
                }
                }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Searching ", Toast.LENGTH_SHORT).show();
                search = etSearch.getText();
                mainViewModel.fetchGifData(search);
                Log.d(TAG, "onClick: getText" + search);
                Glide.with(getContext()).load(test).into(ivTest);
            }
        });
        return view;
    }

    private void setupObservers() {
        mainViewModel.fetchGifData().observe(getViewLifecycleOwner(), new Observer<GiphyResponse>() {
            @Override
            public void onChanged(GiphyResponse gifs) {
                if (gifs != null) {
                    if (gifs != null) {
                        test = gifs.getData().get(0).getImages().getOriginal().getUrl();
                        Log.d("GIPHY RESPONSE:!!!!! ", test);

                        List<DataItem> arg = gifs.getData();
                        int size = arg.size();
                        for (int i = 0; i < size; i++ ) {
                            item = gifs.getData().get(i).getImages().getOriginal().getUrl();
                            gifList.add(item);
                            Log.d(TAG, "onChanged: " + gifList);
                            adaptor.setGifs(gifList);
                        }
                    }


                    } else
                        Toast.makeText(getContext(), "NO DATA", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
//     mainViewModel.getErrorLiveData().observe(this, isError{
//        if (!isError.isEmpty())
//            Toast.makeText(this, isError, Toast.LENGTH_SHORT).show();
//    });

