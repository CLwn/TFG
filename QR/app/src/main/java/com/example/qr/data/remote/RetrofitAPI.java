package com.example.qr.data.remote;

import com.example.qr.data.DataModal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("auth")
    Call<DataModal> createPost(@Body DataModal dataModal);
}
