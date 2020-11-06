package com.example.uts_2201747975.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_2201747975.DrinkActivity;
import com.example.uts_2201747975.OrderActivity;
import com.example.uts_2201747975.R;
import com.example.uts_2201747975.model.Drink;
import com.example.uts_2201747975.model.MyOrder;
import com.example.uts_2201747975.utils.FakeDBHelper;

import java.util.ArrayList;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ItemViewHolder>  {

    private ArrayList<Drink> drinkList;
    private Context c;

    public DrinksAdapter(ArrayList<Drink> drinkList, Context c) {
        this.drinkList = drinkList;
        this.c = c;

    }

    @NonNull
    @Override
    public DrinksAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_list_row, parent, false);
        return new DrinksAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.ItemViewHolder holder, final int position) {
        Drink d = drinkList.get(position);
        holder.drinkLbl.setText(d.getName() +"\n" +d.getPrice().toString());
        holder.drinkImg.setImageResource(d.getImage());

        holder.drinkLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, OrderActivity.class);
                intent.putExtra("DRINKNAME", drinkList.get(position).getName());
                intent.putExtra("DRINKPRICE", drinkList.get(position).getPrice().toString());
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout drinkLayout;
        TextView drinkLbl;
        ImageView drinkImg;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkLayout = itemView.findViewById(R.id.drinkLayout);
            drinkLbl = itemView.findViewById(R.id.drinkLbl);
            drinkImg = itemView.findViewById(R.id.drinkImg);
        }
    }
}
