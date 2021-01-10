package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.uts_2201747975.utils.FakeDBHelper;
import com.example.uts_2201747975.utils.HistoryDBHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    HistoryDBHelper history;
    private static final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        FakeDBHelper.initLocation();

        history = new HistoryDBHelper(this);

//        history.clearDatabase();

    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onHistoryClick(View view){
        Intent i = new Intent(this, HistoryActivity.class);
        startActivity(i);
    }

    public void onFoodClick(View view){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String[] options = {"Choose Restaurant Manually", "Choose Nearby Restaurant"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose !");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        OnGPS();
                    } else {
                        if (ActivityCompat.checkSelfPermission(
                                MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        } else {
                            LatLng latlng =  getCurrLocation();
                            if(latlng != null){
                                FakeDBHelper.choose = 2;
                                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                                i.putExtra("latKey", latlng.latitude);
                                i.putExtra("longKey", latlng.longitude);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Unable to find location", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                else if(which == 1){
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        OnGPS();
                    } else {
                        if (ActivityCompat.checkSelfPermission(
                                MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        } else {
                            LatLng latlng =  getCurrLocation();
                            if(latlng != null){
                                FakeDBHelper.choose = 2;
                                Intent i = new Intent(MainActivity.this, NearbyActivity.class);
                                i.putExtra("latKey", latlng.latitude);
                                i.putExtra("longKey", latlng.longitude);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Unable to find location", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });
        builder.show();
    }

    public void onSnackClick(View view){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String[] options = {"Choose Restaurant Manually", "Choose Nearby Restaurant"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose !");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        OnGPS();
                    } else {
                        if (ActivityCompat.checkSelfPermission(
                                MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        } else {
                            LatLng latlng =  getCurrLocation();
                            if(latlng != null){
                                FakeDBHelper.choose = 1;
                                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                                i.putExtra("latKey", latlng.latitude);
                                i.putExtra("longKey", latlng.longitude);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Unable to find location", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                else if(which == 1){
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        OnGPS();
                    } else {
                        if (ActivityCompat.checkSelfPermission(
                                MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        } else {
                            LatLng latlng =  getCurrLocation();
                            if(latlng != null){
                                FakeDBHelper.choose = 1;
                                Intent i = new Intent(MainActivity.this, NearbyActivity.class);
                                i.putExtra("latKey", latlng.latitude);
                                i.putExtra("longKey", latlng.longitude);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Unable to find location", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
        builder.show();
    }



    public void onDrinkClick(View view){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String[] options = {"Choose Restaurant Manually", "Choose Nearby Restaurant"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose !");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        OnGPS();
                    } else {
                        if (ActivityCompat.checkSelfPermission(
                                MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        } else {
                            LatLng latlng =  getCurrLocation();
                            if(latlng != null){
                                FakeDBHelper.choose = 0;
                                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                                i.putExtra("latKey", latlng.latitude);
                                i.putExtra("longKey", latlng.longitude);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Unable to find location", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                else if(which == 1){
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        OnGPS();
                    } else {
                        if (ActivityCompat.checkSelfPermission(
                                MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        } else {
                            LatLng latlng =  getCurrLocation();
                            if(latlng != null){
                                FakeDBHelper.choose = 0;
                                Intent i = new Intent(MainActivity.this, NearbyActivity.class);
                                i.putExtra("latKey", latlng.latitude);
                                i.putExtra("longKey", latlng.longitude);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Unable to find location", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });
        builder.show();
    }

    public void onMyorderClick(View view){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }
    public void topUpClick(View view){
        Intent intent = new Intent(this, TopUpActivity.class);
        startActivity(intent);
    }

    private LatLng getCurrLocation() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        try {
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            double userLat = lastKnownLocation.getLatitude();
            double userLong = lastKnownLocation.getLongitude();
            LatLng location = new LatLng(userLat, userLong);
            return location;
        } catch(Exception e) {
            return null;
        }
    }

}