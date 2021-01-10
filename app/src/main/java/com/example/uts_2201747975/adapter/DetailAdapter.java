package com.example.uts_2201747975.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_2201747975.DrinkActivity;
import com.example.uts_2201747975.R;
import com.example.uts_2201747975.model.MyOrder;
import com.example.uts_2201747975.model.NearbyRestaurant;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ItemViewHolder> {

    private ArrayList<MyOrder> details;
    private Context c;

    public DetailAdapter(ArrayList<MyOrder> details, Context c) {
        this.details = details;
        this.c = c;
    }

    @NonNull
    @Override
    public DetailAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_list_row, parent, false);
        return new DetailAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        MyOrder mo = details.get(position);
        holder.nameDetail.setText(mo.getDrinkName() +": ");
        holder.priceDetail.setText("Rp. " +mo.getDrinkPrice());
        holder.quantityDetail.setText(" x " +mo.getDrinkQuantity());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameDetail, priceDetail, quantityDetail;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameDetail = itemView.findViewById(R.id.nameDetail);
            priceDetail = itemView.findViewById(R.id.priceDetail);
            quantityDetail = itemView.findViewById(R.id.quantityDetail);
        }
    }
}
