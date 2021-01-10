package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.uts_2201747975.adapter.DrinksAdapter;
import com.example.uts_2201747975.model.Drink;
import com.example.uts_2201747975.utils.FakeDBHelper;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {

    ArrayList<Drink> drinkList;
    RecyclerView drinkRv;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        init();
        String location = getIntent().getStringExtra("location");

        title.setText("Binus Ezyfood " +location);

        FakeDBHelper helper = new FakeDBHelper();

        if(FakeDBHelper.choose == 0){
            drinkList = helper.getAll();
        }
        else if(FakeDBHelper.choose == 1){
            drinkList = helper.getAll1();
        }
        else if(FakeDBHelper.choose == 2){
            drinkList = helper.getAll2();
        }



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
        title = findViewById(R.id.title);
        drinkRv = findViewById(R.id.drinkRv);
    }
}