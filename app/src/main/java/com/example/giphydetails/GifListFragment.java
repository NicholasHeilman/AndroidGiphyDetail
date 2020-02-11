package com.example.giphydetails;


import android.nfc.Tag;
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
import android.widget.Toast;
import com.example.giphydetails.adaptors.GifAdaptor;
import com.example.giphydetails.models.DataItem;
import com.example.giphydetails.models.GiphyResponse;
import com.example.giphydetails.viewmodels.MainViewModel;
import java.util.ArrayList;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class GifListFragment extends Fragment {

    private Button btnSearch;
    private Button btnToggleView;
    private EditText etSearch;
    private GifAdaptor adaptor;
    private Editable search;
    private MainViewModel mainViewModel;
    private ArrayList<String> gifList;
    String item;
    private String test;
    private int offset;


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
        rvGifs.setHasFixedSize(true);

        btnSearch = view.findViewById(R.id.btnSearch);
        etSearch = view.findViewById(R.id.etSearch);
        btnToggleView = view.findViewById(R.id.btnToggleView);
        setupObservers();
        gifList = new ArrayList<>();

        rvGifs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    offset += 25;
                    Toast.makeText(getContext(), "Next" , Toast.LENGTH_LONG).show();
                    mainViewModel.fetchGifData(search, offset);
                }
            }
        });

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
                gifList = new ArrayList<>();
                search = etSearch.getText();
                offset = 0;
                Log.d(TAG, "onClick: Search = " + search);
                if(search == null) {
                    Toast.makeText(getContext(), "Enter Search", Toast.LENGTH_SHORT ).show();
                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("You Forgot to Enter Something ")
                            .show();
                } else {
                    mainViewModel.fetchGifData(search, offset);
                    Toast.makeText(getContext(),"Searching ", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: getText" + search + offset);
                }
            }
        });
        return view;
    }//end onCreateView

    private void setupObservers() {
        mainViewModel.fetchGifData().observe(getViewLifecycleOwner(), new Observer<GiphyResponse>() {
            @Override
            public void onChanged(GiphyResponse gifs) {
                if (gifs != null) {
//                        test = gifs.getData().get(0).getImages().getOriginal().getUrl();
//                        Log.d("GIPHY RESPONSE:!!!!! ", test);
                        List<DataItem> arg = gifs.getData();
                        int size = arg.size();
                        for (int i = 0; i < size; i++ ) {
                            item = gifs.getData().get(i).getImages().getOriginal().getUrl();
                            gifList.add(item);
//                            Log.d(TAG, "onChanged: " + gifList);
                            adaptor.setGifs(gifList);
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

