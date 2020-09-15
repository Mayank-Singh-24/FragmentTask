package com.example.tabswithapi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentImages  extends Fragment {
    private RecyclerView mRecyclerViewImage;
    private ImageAdapter mImageAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_images,container,false);
        mRecyclerViewImage=view.findViewById(R.id.recycler_view_images);
        mRecyclerViewImage.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewImage.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mImageAdapter = new ImageAdapter();
        getAllPhotos();
        return view;
    }
    public void getAllPhotos() {
        NetworkHandler.instance().mJsonPlaceHolderApi.photos().enqueue(new Callback<List<Photos>>(){
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if (response.isSuccessful()) {
                    List<Photos> photos = response.body();
                    mImageAdapter.setData(photos);
                    mRecyclerViewImage.setAdapter(mImageAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Toast.makeText(getContext(), "Went wrong", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "fail");
            }
        } );

    }
}
