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
import android.widget.ImageView;
import android.widget.TextView;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.CartItemRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.models.Order;
import com.ngoctin.intuitionmobile.models.OrderDetail;
import com.ngoctin.intuitionmobile.models.Promotion;
import com.ngoctin.intuitionmobile.services.CartService;
import com.ngoctin.intuitionmobile.services.OrderService;
import com.ngoctin.intuitionmobile.services.PromotionService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends AppCompatActivity {

    int promotionID = 0;
    float totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewCartActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        List<CartItem> cart = CartService.getCart(this);
        TextView tvCartTotalPrice = findViewById(R.id.tvCartTotalPrice);
        EditText edtVoucher = this.findViewById(R.id.edtVoucher);
        String promotionJson = this.getIntent().getStringExtra("promotion");
        Promotion promotion = PromotionService.jsonToPromotion(promotionJson);
        totalPrice = CartService.getCartTotal(cart);
        CartItemRecyclerViewAdapter adapter = new CartItemRecyclerViewAdapter(cart,this,tvCartTotalPrice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView cartRecyclerView = findViewById(R.id.rvCart);
        cartRecyclerView.setAdapter(adapter);
        cartRecyclerView.setLayoutManager(linearLayoutManager);
        if(promotion != null){
            promotionID = promotion.getId();
            edtVoucher.setText(promotion.getName());
            totalPrice -= (totalPrice * promotion.getDiscountPercent() / 100) ;
        }
        EditText edtReceiverAddress = this.findViewById(R.id.edtReceiverAddress);
        tvCartTotalPrice.setText(totalPrice + "");
        Button ApplyVoucherBtn = this.findViewById(R.id.btnApplyVoucher);
        ApplyVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewCartActivity.this, PromotionActivity.class );
                startActivity(intent);
            }
        });

        Button btnForwardCheckOut = this.findViewById(R.id.btnForwardCheckOut);
        btnForwardCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long orderID = System.currentTimeMillis();
                String address = edtReceiverAddress.getText().toString();
                Order order = new Order(orderID,
                        ApplicationUtils.getCurrentUserID(ViewCartActivity.this),
                        promotionID,0,totalPrice,null,address);
                List<OrderDetail> orderDetailList = new ArrayList<>();
                for (CartItem cartItem: cart) {
                    float price = (Integer.parseInt(cartItem.getProduct().getPrice()) * cartItem.getQuantity());
                    orderDetailList.
                            add(new OrderDetail(null,
                                    cartItem.getCartItemID(),
                                    cartItem.getQuantity(),
                                    price,
                                    orderID));
                }
                OrderService.
                        createOrder(ViewCartActivity.this,
                                ApplicationUtils.getJwt(ViewCartActivity.this),
                                order,
                                orderDetailList);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<CartItem> cart = CartService.getCart(this);
        TextView tvCartTotalPrice = findViewById(R.id.tvCartTotalPrice);
        EditText edtVoucher = this.findViewById(R.id.edtVoucher);
        String promotionJson = this.getIntent().getStringExtra("promotion");
        Promotion promotion = PromotionService.jsonToPromotion(promotionJson);
        float totalPrice = CartService.getCartTotal(cart);
        CartItemRecyclerViewAdapter adapter = new CartItemRecyclerViewAdapter(cart,this,tvCartTotalPrice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView cartRecyclerView = findViewById(R.id.rvCart);
        cartRecyclerView.setAdapter(adapter);
        cartRecyclerView.setLayoutManager(linearLayoutManager);
        if(promotion != null){
            edtVoucher.setText(promotion.getName());
            totalPrice -= (totalPrice * promotion.getDiscountPercent() / 100) ;
        }
        tvCartTotalPrice.setText(totalPrice + "");
        Button ApplyVoucherBtn = this.findViewById(R.id.btnApplyVoucher);
        ApplyVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewCartActivity.this, PromotionActivity.class );
                startActivity(intent);
            }
        });
    }
}