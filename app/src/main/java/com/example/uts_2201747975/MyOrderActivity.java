package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.uts_2201747975.adapter.DrinksAdapter;
import com.example.uts_2201747975.adapter.MyOrderAdapter;
import com.example.uts_2201747975.utils.FakeDBHelper;

public class MyOrderActivity extends AppCompatActivity implements OnRefreshViewListner {

    TextView totalLbl;
    ImageButton payNowBtn;
    RecyclerView myOrderRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        init();
        validateZeroQuantity();

        RecyclerView.Adapter adapter = new MyOrderAdapter(FakeDBHelper.myOrders, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myOrderRv.setLayoutManager(layoutManager);
        myOrderRv.setAdapter(adapter);

        calculateTotal();

        payNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });

    }

    public void calculateTotal(){
        int length = FakeDBHelper.myOrders.size();
        int total = 0;
        for(int i = 0 ;  i < length ; i ++){
            total += FakeDBHelper.myOrders.get(i).getDrinkPrice() * FakeDBHelper.myOrders.get(i).getDrinkQuantity();
        }
        totalLbl.setText("Total : Rp. " +total);
    }

    private void validateZeroQuantity() {
        int length = FakeDBHelper.myOrders.size();
        for (int i = 0 ; i < length ; i ++){
            if(FakeDBHelper.myOrders.get(i).getDrinkQuantity() == 0)FakeDBHelper.myOrders.remove(i);
        }

    }

    private void init() {
        totalLbl = findViewById(R.id.totalLbl);
        payNowBtn = findViewById(R.id.payNowBtn);
        myOrderRv = findViewById(R.id.myOrderRv);
    }

    @Override
    public void refreshView() {
        calculateTotal();
    }
}