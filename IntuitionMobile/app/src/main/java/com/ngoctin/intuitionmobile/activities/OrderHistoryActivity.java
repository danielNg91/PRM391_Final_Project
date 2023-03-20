package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.OderHistoryAdapter;
import com.ngoctin.intuitionmobile.services.OrderService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        RecyclerView orderHistoryRecyclerView = this.findViewById(R.id.rvOrderHistory);
        OderHistoryAdapter adapter = new OderHistoryAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        orderHistoryRecyclerView.setLayoutManager(linearLayoutManager);
        OrderService.getOrdersByUserID(this,
                ApplicationUtils.getJwt(this),
                ApplicationUtils.getCurrentUserID(this),
                orderHistoryRecyclerView,adapter);
    }
}