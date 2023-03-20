package com.ngoctin.intuitionmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.activities.PromotionActivity;
import com.ngoctin.intuitionmobile.activities.ViewCartActivity;
import com.ngoctin.intuitionmobile.models.Promotion;
import com.ngoctin.intuitionmobile.services.PromotionService;

import java.util.List;

public class PromotionRecyclerViewAdapter extends RecyclerView.Adapter {

    List<Promotion> promotions;
    Context context;

    public PromotionRecyclerViewAdapter(List<Promotion> promotions, Context context) {
        this.promotions = promotions;
        this.context = context;
    }

    public PromotionRecyclerViewAdapter() {
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View promotionView = inflater.inflate(R.layout.single_voucher_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(promotionView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String voucherUrl = "https://img.timviec.com.vn/2020/10/voucher-la-gi-3.jpg";
        Promotion promotion = promotions.get(position);
        ImageView promotionImage = holder.itemView.findViewById(R.id.promotionImage);
        Glide
                .with(context)
                .load(voucherUrl)
                .centerCrop()
                .into(promotionImage);
        ((TextView)holder.itemView.findViewById(R.id.tvPromotionID)).setText(promotion.getId()+"");
        ((TextView)holder.itemView.findViewById(R.id.tvPromotionCode)).setText(promotion.getName());
        ((TextView)holder.itemView.findViewById(R.id.tvPromotionValue)).setText(promotion.getDiscountPercent()+"");
        ((TextView)holder.itemView.findViewById(R.id.tvPromotionStartDate)).setText(promotion.getCreatedDate());
        ((TextView)holder.itemView.findViewById(R.id.tvPromotionIssuedDate)).setText(promotion.getIssuedDate());
        Button userVoucherButton = holder.itemView.findViewById(R.id.btnUseVoucher);
        userVoucherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ViewCartActivity.class);
                intent.putExtra("promotion", PromotionService.promotionToJson(promotion));
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(promotions != null){
            return promotions.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
