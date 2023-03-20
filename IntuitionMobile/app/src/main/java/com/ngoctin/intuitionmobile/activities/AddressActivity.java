package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.AddressRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.apis.UserAPI;
import com.ngoctin.intuitionmobile.models.Address;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.models.Order;
import com.ngoctin.intuitionmobile.models.OrderDetail;
import com.ngoctin.intuitionmobile.services.AddressService;
import com.ngoctin.intuitionmobile.services.UserService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        RecyclerView addressRecyclerView = this.findViewById(R.id.rvAddresses);
        AddressRecyclerViewAdapter adapter = new AddressRecyclerViewAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        addressRecyclerView.setLayoutManager(linearLayoutManager);
        addressRecyclerView.setAdapter(adapter);
        Button btnBack = this.findViewById(R.id.btnBack);

        SharedPreferences sharedPreferences = AddressActivity.this.getSharedPreferences("user_store", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("currOrder","");
        Order currOrder = gson.fromJson(json,Order.class);
        json = sharedPreferences.getString("currOrderItems","");
        Type type = new TypeToken<List<OrderDetail>>(){}.getType();
        List<OrderDetail> orderDetailList = gson.fromJson(json,type);
        System.out.println("currOrder : " + currOrder);
        System.out.println("Order : " + orderDetailList);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
        UserService.getAddressesByUserID(this,
                ApplicationUtils.getJwt(this),
                ApplicationUtils.getCurrentUserID(this),
                addressRecyclerView,
                adapter);
    }
}