package com.ngoctin.intuitionmobile.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ngoctin.intuitionmobile.activities.OrderSuccessfullyActivity;
import com.ngoctin.intuitionmobile.adapter.PromotionRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.apis.PromotionAPI;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.models.Promotion;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromotionService {


    public static String promotionToJson(Promotion promotion){
        Gson gson = new Gson();
        String returnValue = gson.toJson(promotion);
        return returnValue;
    }

    public static Promotion jsonToPromotion(String json){
        Gson gson = new Gson();
        Promotion returnValue = gson.fromJson(json,Promotion.class);
        return returnValue;
    }

    public static List<Promotion> getPromotions(Activity activity){
        List<Promotion> promotions = null;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_store",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<CartItem>>(){}.getType();
        String json = sharedPreferences.getString("promotions","");
        promotions = gson.fromJson(json, type);
        return promotions;
    }

    public static List<Promotion> getPromotions(Context context){
        List<Promotion> promotions = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_store",context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<CartItem>>(){}.getType();
        String json = sharedPreferences.getString("promotions","");
        promotions = gson.fromJson(json, type);
        return promotions;
    }

    public static boolean updatePromotionList(Activity activity, List<Promotion> promotions){
        try {
            SharedPreferences sp = activity.getSharedPreferences("user_store", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Gson gson = new Gson();
            String promotionJson = gson.toJson(promotions);
            editor.putString("promotions",promotionJson);
            editor.commit();
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public static boolean updatePromotionList(Context context, List<Promotion> promotions){
        try {
            SharedPreferences sp = context.getSharedPreferences("user_store", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Gson gson = new Gson();
            String promotionJson = gson.toJson(promotions);
            editor.putString("promotions",promotionJson);
            editor.commit();
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public static List<Promotion> removeFromCart(Context context, List<Promotion> promotions,Promotion promotion){
        for (Promotion item: promotions) {
            if(item.getId() == promotion.getId()){
                promotions.remove(promotion);
            }
        }
        return promotions;
    }

    public static List<Promotion> removeFromCart(Activity activity, List<Promotion> promotions,Promotion promotion){
        for (Promotion item: promotions) {
            if(item.getId() == promotion.getId()){
                promotions.remove(promotion);
            }
        }
        return promotions;
    }

    public static void getPromotionFromBEByUserID(Activity activity, String jwt, RecyclerView recyclerView, PromotionRecyclerViewAdapter adapter,int userID){
        PromotionAPI.promotionApi
                .getPromotionsFromBEByUserID(jwt, userID)
                .enqueue(new Callback<List<Promotion>>() {
                    @Override
                    public void onResponse(Call<List<Promotion>> call, Response<List<Promotion>> response) {
                        adapter.setPromotions(response.body());
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Promotion>> call, Throwable t) {

                    }
                });
    }

    public static void setUserPromotionByUserID(Context context, String jwt, int promotionID, int userID){
        PromotionAPI.promotionApi
                .setUsedPromotionByUserID(jwt,promotionID,userID)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.code() == 200 && response.body() == true){
                            Intent intent = new Intent(context, OrderSuccessfullyActivity.class);
                            context.startActivity(intent);
                        }else{
                            Toast.makeText(context, "Update User_Promotion Failed : " + response.code() + " - " + response.errorBody(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
    }

}
