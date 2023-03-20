package com.ngoctin.intuitionmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.models.Address;
import com.ngoctin.intuitionmobile.services.AddressService;
import com.ngoctin.intuitionmobile.services.UserService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.List;

public class AddressRecyclerViewAdapter extends RecyclerView.Adapter {

    List<Address> addresses;
    Context context;

    public AddressRecyclerViewAdapter(List<Address> addresses, Context context) {
        this.addresses = addresses;
        this.context = context;
    }

    public AddressRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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
        View addressView = inflater.inflate(R.layout.single_address_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(addressView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Address address = addresses.get(position);
        ((TextView)holder.itemView.findViewById(R.id.tvSingleAddressLayoutNo)).setText((position+1)+"");
        ((TextView)holder.itemView.findViewById(R.id.tvSingleAddressLayoutValue)).setText(address.getValue());
        Button btnSelectAddress = holder.itemView.findViewById(R.id.btnSelectAddress);
        int place = position;
        btnSelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update UI
                addresses.remove(place);
                AddressService.removeAddress(getContext(),
                        AddressService.getAddresses(getContext())
                        ,address.getId());
                notifyDataSetChanged();
                // Call Api
                int addressID = Integer.parseInt(address.getId() + "");
                System.out.println("AddressID : " + addressID);
                UserService.removeAddressByUserID(
                        getContext(),
                        ApplicationUtils.getJwt(getContext()),
                        ApplicationUtils.getCurrentUserID(getContext()),
                        addressID);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(addresses != null){
            return addresses.size();
        }
        return  0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
