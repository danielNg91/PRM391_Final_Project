package com.ngoctin.intuitionmobile.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctin.intuitionmobile.models.RegisterUserRequest;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterAPI {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    RegisterAPI registerApi= new Retrofit.Builder()
            .baseUrl("http://" + ApplicationUtils.getLocalhost() + ":8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegisterAPI.class);

    @POST("register")
    Call<Boolean> register(@Body RegisterUserRequest registerUserRequest);
}
