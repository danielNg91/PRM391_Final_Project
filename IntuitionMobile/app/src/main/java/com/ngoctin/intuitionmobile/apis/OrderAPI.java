package com.ngoctin.intuitionmobile.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctin.intuitionmobile.models.Category;
import com.ngoctin.intuitionmobile.models.Order;
import com.ngoctin.intuitionmobile.models.OrderDetail;
import com.ngoctin.intuitionmobile.models.OrderDetailResponse;
import com.ngoctin.intuitionmobile.models.RegisterUserRequest;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderAPI {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    OrderAPI orderApi = new Retrofit.Builder()
            .baseUrl("http://" + ApplicationUtils.getLocalhost() + ":8080/api/order/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(OrderAPI.class);

    @POST("createOrder")
    Call<Boolean> createOrder(
            @Header("Authorization") String jwt,
            @Body Order order);

    @POST("createOrderDetails")
    Call<Boolean> createOrderDetails(
            @Header("Authorization") String jwt,
            @Body List<OrderDetail> orderDetails);

    @GET("getOrderHistoryByUserID/{userID}")
    Call<List<Order>> getOrderHistoryByUserID(
            @Header("Authorization") String jwt,
            @Path("userID") int userID);


    @GET("getOrderDetailsByOrderID/{orderID}")
    Call<List<OrderDetailResponse>> getOrderDetailsByOrderID(
            @Header("Authorization") String jwt,
            @Path("orderID") Long orderID);
}
