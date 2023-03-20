package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.CartItemRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.apis.ProductAPI;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.models.Product;
import com.ngoctin.intuitionmobile.services.CartService;
import com.ngoctin.intuitionmobile.services.ProductService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductActivity extends AppCompatActivity {

    List<CartItem> cart = null;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        ImageView productImage = findViewById(R.id.singleProductImage);
        TextView  productName = findViewById(R.id.singleProductName);
        TextView  productPrice = findViewById(R.id.singleProductPrice);
        TextView  productQuantity = findViewById(R.id.singleProductAvailable);
        TextView productDesc = findViewById(R.id.singleProductDescription);
        TextView productUrl = findViewById(R.id.tvProductImage);
        CartItemRecyclerViewAdapter cartItemRecyclerViewAdapter = new CartItemRecyclerViewAdapter(SingleProductActivity.this);
        int productID = this.getIntent().getIntExtra("selected_product_id",0);
        ProductService.getProductByID(
                SingleProductActivity.this,
                productImage,productName,productPrice,
                productQuantity,productDesc, productUrl);
        Button addToCartButton = findViewById(R.id.btnAddToCart);
        cart = CartService.getCart(this);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(
                        productName.getText().toString(),
                        productPrice.getText().toString(),
                        productDesc.getText().toString(),
                        productUrl.getText().toString()
                );
                CartItem cartItem = CartService.findCartItemBYID(cart,productID);
                if(cartItem == null){
                    cartItem = new CartItem(productID,product,1);
                    cart.add(cartItem);
                }else{
                    for (CartItem item: cart) {
                        if(item.getCartItemID() == productID){
                            item.setQuantity(item.getQuantity() + 1);
                        }
                    }
                }
                if(CartService.updateCart(SingleProductActivity.this,cart)){
                    Toast.makeText(SingleProductActivity.this, "Add To Cart Succesfully !", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SingleProductActivity.this, "Add To Cart Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView productImage = findViewById(R.id.singleProductImage);
        TextView  productName = findViewById(R.id.singleProductName);
        TextView  productPrice = findViewById(R.id.singleProductPrice);
        TextView  productQuantity = findViewById(R.id.singleProductAvailable);
        TextView productDesc = findViewById(R.id.singleProductDescription);
        TextView productUrl = findViewById(R.id.tvProductImage);
        CartItemRecyclerViewAdapter cartItemRecyclerViewAdapter = new CartItemRecyclerViewAdapter(SingleProductActivity.this);
        int productID = this.getIntent().getIntExtra("selected_product_id",0);
        ProductService.getProductByID(
                SingleProductActivity.this,
                productImage,productName,productPrice,
                productQuantity,productDesc, productUrl);

        btnBack = findViewById(R.id.btnBackToHome);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleProductActivity.this, UserHomeScreenActivity.class);
                startActivity(intent);
            }
        });
        Button addToCartButton = findViewById(R.id.btnAddToCart);
        cart = CartService.getCart(this);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(
                        productName.getText().toString(),
                        productPrice.getText().toString(),
                        productDesc.getText().toString(),
                        productUrl.getText().toString()
                );
                CartItem cartItem = CartService.findCartItemBYID(cart,productID);
                if(cartItem == null){
                    cartItem = new CartItem(productID,product,1);
                    cart.add(cartItem);
                }else{
                    for (CartItem item: cart) {
                        if(item.getCartItemID() == productID){
                            item.setQuantity(item.getQuantity() + 1);
                        }
                    }
                }
                if(CartService.updateCart(SingleProductActivity.this,cart)){
                    Toast.makeText(SingleProductActivity.this, "Add To Cart Succesfully !", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SingleProductActivity.this, "Add To Cart Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}