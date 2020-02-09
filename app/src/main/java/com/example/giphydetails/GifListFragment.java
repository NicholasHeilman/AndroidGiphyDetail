package com.example.giphydetails;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class GifListFragment extends Fragment {

    Button btnSearch;
    Button btnToggleView;
    EditText etSearch;
    RecyclerView rvGifs;

    public GifListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gif_list, container, false);

        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Searching ", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
