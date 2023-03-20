package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ngoctin.intuitionmobile.R;

public class MenuActivity extends AppCompatActivity {

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button signoutButton = this.findViewById(R.id.btnLogout);
        Button updateProfileButton = this.findViewById(R.id.btnForwardUpdateProfile);
        Button forwardCartButton = this.findViewById(R.id.btnForwardCart);
        Button AddressButton = this.findViewById(R.id.btnForwardAddress);
        Button PromotionButton = this.findViewById(R.id.btnForwardPromotion);
        Button MyOrderButton = this.findViewById(R.id.btnOrderHistory);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, UserHomeScreenActivity.class);
                startActivity(intent);
            }
        });

        MyOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,UpdateProfile.class);
                startActivity(intent);
            }
        });
        forwardCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ViewCartActivity.class);
                startActivity(intent);
            }
        });
        AddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AddressActivity.class );
                startActivity(intent);
            }
        });
        PromotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PromotionActivity.class );
                startActivity(intent);
            }
        });

    }
}