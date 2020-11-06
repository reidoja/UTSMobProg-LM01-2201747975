package com.example.uts_2201747975.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_2201747975.OnRefreshViewListner;
import com.example.uts_2201747975.PayActivity;
import com.example.uts_2201747975.R;
import com.example.uts_2201747975.model.MyOrder;
import com.example.uts_2201747975.utils.FakeDBHelper;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ItemViewHolder>{

    private ArrayList<MyOrder> myOrderList;
    private Context c;

    public MyOrderAdapter(ArrayList<MyOrder> myOrderList, Context c){
        this.myOrderList = myOrderList;
        this.c = c;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorder_list_row, parent, false);
        return new MyOrderAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderAdapter.ItemViewHolder holder, final int position) {
        MyOrder myOrder = myOrderList.get(position);
        holder.nameLbl.setText(myOrder.getDrinkName());
        holder.quantityPriceLbl.setText(myOrder.getDrinkQuantity() + " x Rp. " +myOrder.getDrinkPrice() +" = " +myOrder.getDrinkPrice()*myOrder.getDrinkQuantity());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FakeDBHelper.myOrders.remove(position);
                notifyDataSetChanged();
                holder.onRefreshViewListner.refreshView();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrderList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameLbl, quantityPriceLbl;
        ImageButton deleteBtn;
        OnRefreshViewListner onRefreshViewListner;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            onRefreshViewListner = (OnRefreshViewListner)c;
            nameLbl = itemView.findViewById(R.id.nameLbl);
            quantityPriceLbl = itemView.findViewById(R.id.quantityPriceLbl);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
