package com.ngoctin.intuitionmobile.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.activities.SingleProductActivity;
import com.ngoctin.intuitionmobile.adapter.ProductRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.apis.ProductAPI;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductService {

    public static void getProducts(String jwt,  RecyclerView recyclerView, ProductRecyclerViewAdapter productRecyclerViewAdapter, int cateID, String searchValue) {
        if(searchValue == null || searchValue.isEmpty()){
            switch (cateID){
                case 0 :
                    ProductAPI.productApi
                            .getAllProducts(jwt)
                            .enqueue(new Callback<List<Product>>() {
                                @Override
                                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                    productRecyclerViewAdapter.setProducts(response.body());
                                    recyclerView.setAdapter(productRecyclerViewAdapter);
                                }

                                @Override
                                public void onFailure(Call<List<Product>> call, Throwable t) {

                                }
                            });
                    break;
                case 1 :
                    ProductAPI.productApi
                            .getProductsByCateID(jwt,1)
                            .enqueue(new Callback<List<Product>>() {
                                @Override
                                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                    productRecyclerViewAdapter.setProducts(response.body());
                                    recyclerView.setAdapter(productRecyclerViewAdapter);
                                }

                                @Override
                                public void onFailure(Call<List<Product>> call, Throwable t) {

                                }
                            });
                    break;
                case 2 :
                    ProductAPI.productApi
                            .getProductsByCateID(jwt,2)
                            .enqueue(new Callback<List<Product>>() {
                                @Override
                                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                    productRecyclerViewAdapter.setProducts(response.body());
                                    recyclerView.setAdapter(productRecyclerViewAdapter);
                                }

                                @Override
                                public void onFailure(Call<List<Product>> call, Throwable t) {

                                }
                            });
                    break;
                default:break;
            }
        }else{
            // getProductByID
            ProductAPI.productApi
                    .getProductByName(jwt,searchValue)
                    .enqueue(new Callback<List<Product>>() {
                        @Override
                        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                            productRecyclerViewAdapter.setProducts(response.body());
                            recyclerView.setAdapter(productRecyclerViewAdapter);
                        }

                        @Override
                        public void onFailure(Call<List<Product>> call, Throwable t) {

                        }
                    });
        }

    }

    public static void getProductByID(Activity activity, ImageView productImage,
                                      TextView productName, TextView productPrice,
                                      TextView productQuantity, TextView productDesc,
                                      TextView productUrl){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_store", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("authenticated_user","");
        AuthenticatedUser authenticatedUser = gson.fromJson(json,AuthenticatedUser.class);
        int productID = activity.getIntent().getIntExtra("selected_product_id",0);
        ProductAPI.productApi
                .getProductByID(authenticatedUser.getJwt(),productID)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Glide.
                                with(activity)
                                .load(response.body().getUrl())
                                .centerCrop()
                                .into(productImage);
                        productName.setText("Name : " + response.body().getName());
                        productPrice.setText(response.body().getPrice() + "");
                        productQuantity.setText("Available : " + response.body().getQuantity());
                        productDesc.setText("Description : " + response.body().getDescription());
                        productUrl.setText(response.body().getUrl());
                    }
                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        System.out.println("Exception : " + t.getMessage());
                    }
                });
    }



}
