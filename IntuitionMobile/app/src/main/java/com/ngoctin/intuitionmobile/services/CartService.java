package com.ngoctin.intuitionmobile.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ngoctin.intuitionmobile.models.CartItem;

import java.lang.reflect.Type;
import java.util.List;

public class CartService {

    public static CartItem findCartItemBYID(List<CartItem> cart, int productID){
        CartItem returnValue = null;
        System.out.println("findCartItemBYID : " + productID);
        for (CartItem cartItem: cart) {
            if(cartItem.getCartItemID() == productID){
                returnValue = cartItem;
            }
        }
        return returnValue;
    }

    public static int getCartTotal(List<CartItem> cart){
        int total = 0;
        for (CartItem cartItem: cart) {
            total += ((Integer.parseInt(cartItem.getProduct().getPrice())) * cartItem.getQuantity());
        }
        return total;
    }

    public static int setCartTotal(List<CartItem> cart){
        int total = 0;
        for (CartItem cartItem: cart) {
            total += ((Integer.parseInt(cartItem.getProduct().getPrice())) * cartItem.getQuantity());
        }
        return total;
    }


    public static List<CartItem> updateCartItemQuantity(Context context, List<CartItem> cart,int cartID ,int value,int type){
        if(type == 0){
            for (CartItem cartItem : cart ) {
                if(cartItem.getCartItemID() == cartID){
                    cartItem.setQuantity(cartItem.getQuantity() - value);
                    updateCart(context,cart);
                }
            }
        }else{
            for (CartItem cartItem : cart ) {
                if(cartItem.getCartItemID() == cartID){
                    cartItem.setQuantity(cartItem.getQuantity() + value);
                    updateCart(context,cart);
                }
            }
        }
        return cart;
    }

    public static List<CartItem> removeFromCart(Context context, List<CartItem> cart,int cartID){
        System.out.println("CartService - removeFromCart : " + cartID);
        for (CartItem cartItem: cart) {
            System.out.println("CartService - cartItemSingID : " + cartItem.getCartItemID());
            if(cartItem.getCartItemID() == cartID){
                cart.remove(cartItem);
                System.out.println("CartService - cart : " + cart.toArray());
                updateCart(context,cart);
            }
        }
        return cart;
    }

    public static List<CartItem> getCart(Activity activity){
        List<CartItem> cart = null;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_store",activity.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<CartItem>>(){}.getType();
        String json = sharedPreferences.getString("cart","");
        cart = gson.fromJson(json, type);
        return cart;
    }

    public static List<CartItem> getCart(Context context){
        List<CartItem> cart = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_store",context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<CartItem>>(){}.getType();
        String json = sharedPreferences.getString("cart","");
        cart = gson.fromJson(json, type);
        return cart;
    }

    public static boolean updateCart(Activity activity,List<CartItem> cart){
        try {
            SharedPreferences sp = activity.getSharedPreferences("user_store", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Gson gson = new Gson();
            String cartJson = gson.toJson(cart);
            editor.putString("cart",cartJson);
            editor.commit();
            return true;
        }catch (Exception exception){
            return false;
        }
    }
    public static boolean updateCart(Context context,List<CartItem> cart){
        try {
            SharedPreferences sp = context.getSharedPreferences("user_store", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Gson gson = new Gson();
            String cartJson = gson.toJson(cart);
            editor.putString("cart",cartJson);
            editor.commit();
            return true;
        }catch (Exception exception){
            return false;
        }
    }

}
