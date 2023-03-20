package com.ngoctin.intuitionmobile.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctin.intuitionmobile.models.Category;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CategoryAPI {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    CategoryAPI categoryApi = new Retrofit.Builder()
            .baseUrl("http://" + ApplicationUtils.getLocalhost() + ":8080/api/category/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CategoryAPI.class);

    @GET("searchByLikeName/{name}")
    Call<List<Category>> searchCategories(
            @Header("Authorization") String jwt,
            @Path("name") String searchValue);

}
