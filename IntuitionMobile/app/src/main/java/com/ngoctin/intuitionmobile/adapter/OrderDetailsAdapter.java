package com.ngoctin.intuitionmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.models.OrderDetail;
import com.ngoctin.intuitionmobile.models.OrderDetailResponse;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter {

    Context context;
    List<OrderDetailResponse> orderDetailList;

    public OrderDetailsAdapter(Context context, List<OrderDetailResponse> orderDetailList) {
        this.context = context;
        this.orderDetailList = orderDetailList;
    }

    public OrderDetailsAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<OrderDetailResponse> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailResponse> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View orderDetailView = inflater.inflate(R.layout.single_order_detail_layout,parent,false);
        OrderDetailsAdapter.ViewHolder viewHolder = new ViewHolder(orderDetailView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderDetailResponse orderDetailResponse = orderDetailList.get(position);
        ((TextView)holder.itemView.findViewById(R.id.tvOrderDetailID)).setText(orderDetailResponse.getId()+"");
        ((TextView)holder.itemView.findViewById(R.id.tvOrderDetailProductName)).setText(orderDetailResponse.getProductName());
        ((TextView)holder.itemView.findViewById(R.id.tvOrderDetailQuantity)).setText(orderDetailResponse.getQuantity()+"");
        ((TextView)holder.itemView.findViewById(R.id.tvOrderDetailTotalPrice)).setText(orderDetailResponse.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        if(orderDetailList != null){
            return orderDetailList.size();
        }
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
