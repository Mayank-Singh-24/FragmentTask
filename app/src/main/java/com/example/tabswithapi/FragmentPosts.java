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

public class FragmentPosts extends Fragment {
    private RecyclerView mRecyclerViewInfoUser;
    private InfoUserAdapter mInfoUserAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_posts,container,false);
        mRecyclerViewInfoUser=view.findViewById(R.id.recycler_view_posts);
        mRecyclerViewInfoUser.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewInfoUser.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mInfoUserAdapter = new InfoUserAdapter();
        getAllInfoUsers();
        return view;
    }
    public void getAllInfoUsers() {
        NetworkHandler.instance().mJsonPlaceHolderApi.users().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    mInfoUserAdapter.setData(users);
                    mRecyclerViewInfoUser.setAdapter(mInfoUserAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getContext(), "Went wrong", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "fail");
            }

        });
    }
}
