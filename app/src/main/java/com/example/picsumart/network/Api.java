package com.example.picsumart.network;

import android.telecom.Call;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    //base_url
    private static String BASE_URL = "https://picsum.photos/";

    //parse json data to gson from url
    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Api_Interface api_interface(){
        return getClient().create(Api_Interface.class);
    }
}

