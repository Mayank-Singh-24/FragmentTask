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

public class FragmentUsers extends Fragment {
    private RecyclerView mRecyclerViewUser;
    private UserAdapter mUserAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);
        mRecyclerViewUser = view.findViewById(R.id.recycler_view_user);
        mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewUser.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mUserAdapter = new UserAdapter();
        getAllUsers();
        return view;
    }

    public void getAllUsers() {
        NetworkHandler.instance().mJsonPlaceHolderApi.posts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    mUserAdapter.setData(posts);
                    mRecyclerViewUser.setAdapter(mUserAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getContext(), "Went wrong", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "fail");
            }
        });
    }
}
