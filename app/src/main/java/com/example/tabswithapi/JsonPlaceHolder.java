package com.example.tabswithapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    String BASE_URL = "https://jsonplaceholder.typicode.com";

    @GET("posts")
    Call<List<Post>> posts();

    @GET("users")
    Call<List<User>> users();

    @GET("photos")
    Call<List<Photos>> photos();
}
