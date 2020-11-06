package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.uts_2201747975.adapter.DrinksAdapter;
import com.example.uts_2201747975.model.Drink;
import com.example.uts_2201747975.utils.FakeDBHelper;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {

    ArrayList<Drink> drinkList;
    RecyclerView drinkRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        init();

        FakeDBHelper helper = new FakeDBHelper();
        drinkList = helper.getAll();

        RecyclerView.Adapter adapter = new DrinksAdapter(drinkList, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        drinkRv.setLayoutManager(layoutManager);
        drinkRv.setAdapter(adapter);


    }

    public void myOrderClick(View view){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }

    private void init() {
        drinkRv = findViewById(R.id.drinkRv);
    }
}