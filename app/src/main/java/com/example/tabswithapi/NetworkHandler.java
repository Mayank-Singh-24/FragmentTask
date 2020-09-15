package com.example.tabswithapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHandler {
    private static NetworkHandler mNetworkHandler = new NetworkHandler();

    private void NetworkHandler() {
    }

    public static NetworkHandler instance() {
        return mNetworkHandler;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(JsonPlaceHolder.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public JsonPlaceHolder mJsonPlaceHolderApi = retrofit.create(JsonPlaceHolder.class);

}
