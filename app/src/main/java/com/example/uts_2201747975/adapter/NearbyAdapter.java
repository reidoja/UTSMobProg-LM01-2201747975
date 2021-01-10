package com.example.uts_2201747975.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_2201747975.DrinkActivity;
import com.example.uts_2201747975.MainActivity;
import com.example.uts_2201747975.NearbyActivity;
import com.example.uts_2201747975.OnRefreshViewListner;
import com.example.uts_2201747975.R;
import com.example.uts_2201747975.model.MyOrder;
import com.example.uts_2201747975.model.NearbyRestaurant;
import com.example.uts_2201747975.utils.FakeDBHelper;

import java.util.ArrayList;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.ItemViewHolder> {

    private ArrayList<NearbyRestaurant> nearbyRestaurants;
    private Context c;

    public NearbyAdapter(ArrayList<NearbyRestaurant> nearbyRestaurants, Context c){
        this.nearbyRestaurants = nearbyRestaurants;
        this.c = c;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_list_row, parent, false);
        return new NearbyAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final NearbyRestaurant nearbyRestaurant = nearbyRestaurants.get(position);
        holder.restaurantLbl.setText(nearbyRestaurant.getName());
        holder.distanceValue.setText(nearbyRestaurant.getDistance() +" Km");
        holder.nearbyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FakeDBHelper.locationRestaurant = nearbyRestaurant.getName();
                Intent i = new Intent(c, DrinkActivity.class);
                i.putExtra("location", nearbyRestaurant.getName());
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nearbyRestaurants.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantLbl, distanceValue;
        RelativeLayout nearbyLayout;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantLbl = itemView.findViewById(R.id.restaurantLbl);
            distanceValue = itemView.findViewById(R.id.distanceValue);
            nearbyLayout = itemView.findViewById(R.id.nearbyLayout);
        }
    }
}
