package com.ngoctin.intuitionmobile.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctin.intuitionmobile.models.Promotion;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PromotionAPI {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    PromotionAPI promotionApi = new Retrofit.Builder()
            .baseUrl("http://" + ApplicationUtils.getLocalhost() + ":8080/api/promotion/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(PromotionAPI.class);

    @GET("getPromotionByUserID/{userID}")
    Call<List<Promotion>> getPromotionsFromBEByUserID(
            @Header("Authorization") String jwt,
            @Path("userID") int userID);

    @PUT("setUserPromotionByUserID/{promotionID}/{userID}")
    Call<Boolean> setUsedPromotionByUserID(
            @Header("Authorization") String jwt,
            @Path("promotionID") int promotionID,
            @Path("userID") int userID);

}
