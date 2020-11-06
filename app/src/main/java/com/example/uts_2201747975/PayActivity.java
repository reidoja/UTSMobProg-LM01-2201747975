package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.uts_2201747975.adapter.PayAdapter;
import com.example.uts_2201747975.utils.FakeDBHelper;

public class PayActivity extends AppCompatActivity {

    TextView totalLbl;
    ImageButton menuBtn;
    RecyclerView payOrderRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        init();

        RecyclerView.Adapter adapter = new PayAdapter(FakeDBHelper.myOrders, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        payOrderRv.setLayoutManager(layoutManager);
        payOrderRv.setAdapter(adapter);

        calculateTotal();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FakeDBHelper.myOrders.removeAll(FakeDBHelper.myOrders);
                Intent intent = new Intent(PayActivity.this, MainActivity.class);
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

    private void init() {
        totalLbl = findViewById(R.id.totalLbl);
        menuBtn = findViewById(R.id.menuBtn);
        payOrderRv = findViewById(R.id.payOrderRv);
    }
}