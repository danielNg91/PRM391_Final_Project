package com.ngoctin.intuitionmobile.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ngoctin.intuitionmobile.activities.AddressActivity;
import com.ngoctin.intuitionmobile.apis.UserAPI;
import com.ngoctin.intuitionmobile.models.Address;
import com.ngoctin.intuitionmobile.models.CartItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressService {

    public static MutableLiveData<List<Address>> observableAddress = new MutableLiveData<>();
    public static boolean updateAddress(Context context,List<Address> addresses){
        try {
            SharedPreferences sp = context.getSharedPreferences("user_store", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Gson gson = new Gson();
            String addressesJson = gson.toJson(addresses);
            editor.putString("addresses",addressesJson);
            editor.commit();
            return true;
        }catch (Exception exception){
            return false;
        }
    }
    public static boolean updateAddress(Activity activity, List<Address> addresses){
        try {
            SharedPreferences sp = activity.getSharedPreferences("user_store", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Gson gson = new Gson();
            String addressesJson = gson.toJson(addresses);
            editor.putString("addresses",addressesJson);
            editor.commit();
            return true;
        }catch (Exception exception){
            return false;
        }
    }
    public static List<Address> getAddresses(Context context){
        List<Address> addresses = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_store",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Address>>(){}.getType();
        String json = sharedPreferences.getString("addresses","");
        addresses = gson.fromJson(json, type);
        return addresses;
    }
    public static List<Address> getAddresses(Activity activity){
        List<Address> addresses = null;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_store",activity.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Address>>(){}.getType();
        String json = sharedPreferences.getString("addresses","");
        addresses = gson.fromJson(json, type);
        return addresses;
    }
    public static List<Address> removeAddress(Context context, List<Address> addresses, Long addressID){
        for (Address address: addresses) {
            if(address.getId().equals(addressID)){
                addresses.remove(address);
            }
        }
        updateAddress(context,addresses);
        return addresses;
    }
    public static List<Address> addToAddress(Context context, Address address, List<Address> addresses){
        addresses.add(address);
        updateAddress(context, addresses);
        return addresses;
    }
}
