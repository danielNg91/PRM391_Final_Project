package com.ngoctin.intuitionmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.activities.ViewOrderDetailsActivity;
import com.ngoctin.intuitionmobile.models.Order;
import com.ngoctin.intuitionmobile.models.OrderHistory;

import java.util.List;

public class OderHistoryAdapter extends RecyclerView.Adapter {

    List<Order> orders;
    Context context;

    public OderHistoryAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    public OderHistoryAdapter(Context context) {
        this.context = context;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
        View orderHistoryView = inflater.inflate(R.layout.single_order_history_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(orderHistoryView);
        // get product_item_layout
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Order order = orders.get(position);
        ((TextView)holder.itemView.findViewById(R.id.tvOrderHistoryNo)).setText(order.getId()+"");
        ((TextView)holder.itemView.findViewById(R.id.tvOrderHistoryTotalPrice)).setText(order.getOrderPrice()+"");
        ((TextView)holder.itemView.findViewById(R.id.tvCreatedDate)).setText(order.getCreatedDate());
        Button viewDetailsOrderHistoryBtn = holder.itemView.findViewById(R.id.btnViewOrderHistoryDetail);
        viewDetailsOrderHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewOrderDetailsActivity.class);
                intent.putExtra("order_id",order.getId().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(orders != null){
            return orders.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
