package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.PromotionRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.models.Promotion;
import com.ngoctin.intuitionmobile.services.PromotionService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.List;


public class PromotionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        List<Promotion> promotions = PromotionService.getPromotions(this);
        PromotionRecyclerViewAdapter adapter
                = new PromotionRecyclerViewAdapter(promotions,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        RecyclerView promotionRecyclerView = this.findViewById(R.id.rvPromotion);
        promotionRecyclerView.setLayoutManager(gridLayoutManager);
        PromotionService.
                getPromotionFromBEByUserID(
                        this,
                        ApplicationUtils.getJwt(this),
                        promotionRecyclerView,
                        adapter,
                        ApplicationUtils.getCurrentUserID(this));

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromotionActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}