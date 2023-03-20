package com.ngoctin.intuitionmobile.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctin.intuitionmobile.models.Category;
import com.ngoctin.intuitionmobile.models.Product;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ProductAPI {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ProductAPI productApi = new Retrofit.Builder()
            .baseUrl("http://" + ApplicationUtils.getLocalhost() + ":8080/api/product/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ProductAPI.class);

    @GET("getAllProducts")
    Call<List<Product>> getAllProducts(
            @Header("Authorization") String jwt);

    @GET("getProducts/{cateID}")
    Call<List<Product>> getProductsByCateID(
            @Header("Authorization") String jwt,
            @Path("cateID") int cateID);

    @GET("getProductByID/{productID}")
    Call<Product> getProductByID(
            @Header("Authorization") String jwt,
            @Path("productID") int productID);

    @GET("searchByLikeName/{name}")
    Call<List<Product>> getProductByName(
            @Header("Authorization") String jwt,
            @Path("name") String productName);


}
