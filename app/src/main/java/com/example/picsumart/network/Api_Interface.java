package com.example.picsumart.network;

import com.example.picsumart.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_Interface {

    @GET("list")
    Call<List<Photo>> getPhotos();
}
