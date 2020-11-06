package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onDrinkClick(View view){
        Intent intent = new Intent(this, DrinkActivity.class);
        startActivity(intent);
    }

    public void onMyorderClick(View view){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }

}