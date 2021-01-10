package com.example.uts_2201747975;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.uts_2201747975.model.Drink;
import com.example.uts_2201747975.utils.FakeDBHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double latitude, longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitude = getIntent().getDoubleExtra("latKey", 0);
        longitude = getIntent().getDoubleExtra("longKey", 0);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i  = 0 ; i < FakeDBHelper.latLngs.size() ; i ++){
            mMap.addMarker(new MarkerOptions().position(FakeDBHelper.latLngs.get(i)).title(FakeDBHelper.title.get(i)));
        }


        LatLng latLng= new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Your Location"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(10).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals("Your Location")){
                    return false;
                }
                FakeDBHelper.locationRestaurant = marker.getTitle();
                Intent i = new Intent(MapsActivity.this, DrinkActivity.class);
                i.putExtra("location", marker.getTitle());
                startActivity(i);
                return false;
            }
        });

    }
}