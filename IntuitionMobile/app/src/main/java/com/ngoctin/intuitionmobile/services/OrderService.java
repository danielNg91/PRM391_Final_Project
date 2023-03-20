package com.ngoctin.intuitionmobile.services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ngoctin.intuitionmobile.activities.OrderSuccessfullyActivity;
import com.ngoctin.intuitionmobile.adapter.OderHistoryAdapter;
import com.ngoctin.intuitionmobile.adapter.OrderDetailsAdapter;
import com.ngoctin.intuitionmobile.apis.OrderAPI;
import com.ngoctin.intuitionmobile.apis.PromotionAPI;
import com.ngoctin.intuitionmobile.models.Order;
import com.ngoctin.intuitionmobile.models.OrderDetail;
import com.ngoctin.intuitionmobile.models.OrderDetailResponse;
import com.ngoctin.intuitionmobile.models.OrderHistory;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderService {

    public static void createOrder(Context context,String jwt ,Order order, List<OrderDetail> orderDetailList){
        OrderAPI.orderApi
                .createOrder(jwt,order)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        System.out.println("createOrder : " + response.body() + " : message : " + response.message());
                        if(response.code() == 200 && response.body() == true){
                            OrderAPI.orderApi
                                    .createOrderDetails(jwt,orderDetailList)
                                    .enqueue(new Callback<Boolean>() {
                                        @Override
                                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                            PromotionService
                                                    .setUserPromotionByUserID(context,jwt,
                                                            order.getPromotionId(),
                                                            ApplicationUtils.getCurrentUserID(context));
                                        }
                                        @Override
                                        public void onFailure(Call<Boolean> call, Throwable t) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
    }

    public static void getOrdersByUserID(Context context, String jwt,int userID ,RecyclerView recyclerView, OderHistoryAdapter adapter){
        OrderAPI.orderApi
                .getOrderHistoryByUserID(jwt, userID)
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                        if(response.code() == 200 && response != null){
                            adapter.setOrders(response.body());
                            recyclerView.setAdapter(adapter);
                        }else{
                            Toast.makeText(context, "View Order His Failed !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {

                    }
                });
    }

    public static void getOrderDetailsByOrderID(Context context, String jwt,Long orderID ,RecyclerView recyclerView, OrderDetailsAdapter adapter){
        OrderAPI.orderApi
                .getOrderDetailsByOrderID(jwt, orderID)
                .enqueue(new Callback<List<OrderDetailResponse>>() {
                    @Override
                    public void onResponse(Call<List<OrderDetailResponse>> call, Response<List<OrderDetailResponse>> response) {
                        if(response.code() == 200 && response != null){
                            adapter.setOrderDetailList(response.body());
                            recyclerView.setAdapter(adapter);
                        }else{
                            Toast.makeText(context, "View Order His Failed !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<OrderDetailResponse>> call, Throwable t) {

                    }
                });
    }

}
