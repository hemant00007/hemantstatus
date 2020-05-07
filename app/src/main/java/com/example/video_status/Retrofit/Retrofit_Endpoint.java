package com.example.video_status.Retrofit;

import android.content.Context;
import android.content.Intent;

import com.example.video_status.Model.VideoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Retrofit_Endpoint {


    @GET("api-fetch-all.php")
    Call<List<VideoModel>> getvideolist();
}
