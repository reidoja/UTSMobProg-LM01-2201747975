package com.example.uts_2201747975.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_2201747975.DrinkActivity;
import com.example.uts_2201747975.R;
import com.example.uts_2201747975.model.History;
import com.example.uts_2201747975.model.NearbyRestaurant;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemViewHolder> {

    private ArrayList<History> histories;
    private Context c;

    public HistoryAdapter(ArrayList<History> histories, Context c) {
        this.histories = histories;
        this.c = c;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_row, parent, false);
        return new HistoryAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        History history = histories.get(position);
        holder.restaurantTitle.setText("Location: " +history.getLocation());
        holder.dateLbl.setText(history.getDate());
        holder.totalPrice.setText("Total: Rp. " +history.getTotal());

        RecyclerView.Adapter adapter = new DetailAdapter(history.getTransaction(), c);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(c);
        holder.detailsRv.setLayoutManager(layoutManager);
        holder.detailsRv.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantTitle, dateLbl, totalPrice;
        RecyclerView detailsRv;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantTitle = itemView.findViewById(R.id.restaurantTitle);
            dateLbl = itemView.findViewById(R.id.dateLbl);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            detailsRv = itemView.findViewById(R.id.detailsRv);
        }
    }
}
