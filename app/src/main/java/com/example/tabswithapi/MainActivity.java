package com.example.tabswithapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewUser;
    private UserAdapter mUserAdapter;
    private RecyclerView mRecyclerViewInfoUser;
    private InfoUserAdapter mInfoUserAdapter;
    private RecyclerView mRecyclerViewImage;
    private ImageAdapter mImageAdapter;
    public int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.posts:
                                selectedFragment = new FragmentPosts();
                                flag=1;
                                break;
                            case R.id.images:
                                selectedFragment = new FragmentImages();
                                flag=2;
                                break;
                            case R.id.users:
                                flag=3;
                                selectedFragment = new FragmentUsers();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                selectedFragment).commit();
                        return true;
                    }
                };
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                    new FragmentPosts()).commit();

        }

        if(flag==1){
            mRecyclerViewInfoUser=findViewById(R.id.recycler_view_user);
            mRecyclerViewInfoUser.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerViewInfoUser.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mInfoUserAdapter = new InfoUserAdapter();
            getAllInfoUsers();
        }
        if(flag==2){
            mRecyclerViewImage=findViewById(R.id.recycler_view_images);
            mRecyclerViewImage.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerViewImage.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mImageAdapter = new ImageAdapter();
            getAllPhotos();
        }
        if(flag==3){
            mRecyclerViewUser = findViewById(R.id.recycler_view_posts);
            mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerViewUser.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mUserAdapter = new UserAdapter();
            getAllUsers();

        }







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
                Toast.makeText(MainActivity.this, "Went wrong", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "fail");
            }
        });
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
                Toast.makeText(MainActivity.this, "Went wrong", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "fail");
            }
        } );

    }
    public void getAllInfoUsers() {
        NetworkHandler.instance().mJsonPlaceHolderApi.users().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    mInfoUserAdapter.setData(users);
                    mRecyclerViewUser.setAdapter(mInfoUserAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Went wrong", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "fail");
            }
        });
    }
}