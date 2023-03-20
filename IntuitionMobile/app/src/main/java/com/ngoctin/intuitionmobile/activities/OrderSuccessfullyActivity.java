package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.services.CartService;
import com.ngoctin.intuitionmobile.services.PromotionService;

import java.util.ArrayList;
import java.util.List;

public class OrderSuccessfullyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_successfully);
        Button btnContinueToShopping = this.findViewById(R.id.btnContinueToShopping);
        btnContinueToShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<CartItem> cart = new ArrayList<>();
                CartService.updateCart(OrderSuccessfullyActivity.this,cart);
                Intent intent = new Intent(OrderSuccessfullyActivity.this,UserHomeScreenActivity.class);
                startActivity(intent);
            }
        });


    }
}