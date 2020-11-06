package com.example.uts_2201747975.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_2201747975.R;
import com.example.uts_2201747975.model.MyOrder;

import java.util.ArrayList;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.ItemViewHolder> {

    private ArrayList<MyOrder> payOrderList;
    private Context c;

    public PayAdapter(ArrayList<MyOrder> payOrderList, Context c){
        this.payOrderList = payOrderList;
        this.c = c;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_list_row, parent, false);
        return new PayAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        MyOrder myOrder = payOrderList.get(position);
        holder.nameLbl.setText(myOrder.getDrinkName());
        holder.quantityPriceLbl.setText(myOrder.getDrinkQuantity() + " x Rp. " +myOrder.getDrinkPrice() +" = " +myOrder.getDrinkPrice()*myOrder.getDrinkQuantity());

    }

    @Override
    public int getItemCount() {
        return payOrderList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameLbl, quantityPriceLbl;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLbl = itemView.findViewById(R.id.nameLbl);
            quantityPriceLbl = itemView.findViewById(R.id.quantityPriceLbl);
        }
    }
}
