package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Location;
import android.os.Bundle;

import com.example.uts_2201747975.adapter.DrinksAdapter;
import com.example.uts_2201747975.adapter.NearbyAdapter;
import com.example.uts_2201747975.model.NearbyRestaurant;
import com.example.uts_2201747975.utils.FakeDBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NearbyActivity extends AppCompatActivity {

    RecyclerView nearbyRv;
    private Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        init();

        latitude = getIntent().getDoubleExtra("latKey", 0);
        longitude = getIntent().getDoubleExtra("longKey", 0);

        ArrayList<NearbyRestaurant> nearbyRestaurants = sortedNearby();

        RecyclerView.Adapter adapter = new NearbyAdapter(nearbyRestaurants, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        nearbyRv.setLayoutManager(layoutManager);
        nearbyRv.setAdapter(adapter);


    }

    private void init() {
        nearbyRv = findViewById(R.id.nearbyRv);
    }

    private ArrayList<NearbyRestaurant> sortedNearby(){

        ArrayList<NearbyRestaurant> nearbyRestaurants = new ArrayList<>();

        int length = FakeDBHelper.latLngs.size();

        for(int i = 0 ; i < length ; i ++ ){
            String name = FakeDBHelper.title.get(i);

            Location loc1 = new Location("");
            loc1.setLatitude(latitude);
            loc1.setLongitude(longitude);

            Location loc2 = new Location("");
            loc2.setLatitude(FakeDBHelper.latLngs.get(i).latitude);
            loc2.setLongitude(FakeDBHelper.latLngs.get(i).longitude);

            float distanceInMeters = loc1.distanceTo(loc2) / 1000;

            DecimalFormat df3 = new DecimalFormat("#.###");

            NearbyRestaurant nr= new NearbyRestaurant(name, Double.valueOf(df3.format(distanceInMeters)));
            nearbyRestaurants.add(nr);
        }

        Collections.sort(nearbyRestaurants);

        return nearbyRestaurants;
    }
}