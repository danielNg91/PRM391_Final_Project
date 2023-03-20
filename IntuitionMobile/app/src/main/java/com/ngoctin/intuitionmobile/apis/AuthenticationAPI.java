package com.ngoctin.intuitionmobile.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthenticationAPI{
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    AuthenticationAPI authenticationAPI = new Retrofit.Builder()
            .baseUrl("http://" + ApplicationUtils.getLocalhost() + ":8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuthenticationAPI.class);


    @FormUrlEncoded
    @POST("login")
    Call<String> getJasonWebToken(@Field("username") String username,
                                  @Field("password") String password
    );

    @GET("user/getAuthenticatedUser")
    Call<AuthenticatedUser> getAuthenticatedUser(@Header("Authorization") String jwt);
}
