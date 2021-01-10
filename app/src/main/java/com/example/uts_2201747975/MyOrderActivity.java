package com.example.uts_2201747975;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uts_2201747975.adapter.DrinksAdapter;
import com.example.uts_2201747975.adapter.MyOrderAdapter;
import com.example.uts_2201747975.utils.FakeDBHelper;
import com.example.uts_2201747975.utils.HistoryDBHelper;

import java.time.LocalDate;

public class MyOrderActivity extends AppCompatActivity implements OnRefreshViewListner {

    TextView totalLbl, balancePay;
    Button topUpOrderBtn;
    ImageButton payNowBtn;
    RecyclerView myOrderRv;
    String MyPref = "MyPrefs";
    Integer balance;
    int total;
    HistoryDBHelper history;

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

        total = calculateTotal();

        SharedPreferences sp=getSharedPreferences(MyPref, MODE_PRIVATE);

        balance = sp.getInt("balance", 0);

        balancePay.setText("Balance : Rp. " +balance);

        payNowBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(balance < total){
                    Toast.makeText(MyOrderActivity.this, "Top up first", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(FakeDBHelper.myOrders.isEmpty()){
                    Toast.makeText(MyOrderActivity.this, "Make order first", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor edit=getSharedPreferences(MyPref, MODE_PRIVATE).edit();
                int amount = balance - total;
                edit.putInt("balance",amount);
                edit.commit();
                insertHistory();
                Intent intent = new Intent(MyOrderActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });

        topUpOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderActivity.this, TopUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public int calculateTotal(){
        int length = FakeDBHelper.myOrders.size();
        int total = 0;
        for(int i = 0 ;  i < length ; i ++){
            total += FakeDBHelper.myOrders.get(i).getDrinkPrice() * FakeDBHelper.myOrders.get(i).getDrinkQuantity();
        }
        totalLbl.setText("Total : Rp. " +total);
        return total;
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
        balancePay = findViewById(R.id.balancePay);
        topUpOrderBtn = findViewById(R.id.topUpOrderBtn);
        history = new HistoryDBHelper(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void insertHistory(){
        LocalDate currentDate = LocalDate.now();
        int len = currentDate.getMonth().toString().length();
        String month = currentDate.getMonth().toString().substring(1,len).toLowerCase();
        char firstMonth = currentDate.getMonth().toString().charAt(0);
        String dateNow = firstMonth+month + "/" + currentDate.getDayOfMonth() +"/" +currentDate.getYear();
        String order = "";
        int total = 0;
        int size = FakeDBHelper.myOrders.size();

        for(int i = 0 ; i < size-1 ; i ++){
            order = order  +FakeDBHelper.myOrders.get(i).getDrinkName()+":"+FakeDBHelper.myOrders.get(i).getDrinkPrice()+":"+FakeDBHelper.myOrders.get(i).getDrinkQuantity()+",";
        }
        order = order +FakeDBHelper.myOrders.get(size-1).getDrinkName()+":"+FakeDBHelper.myOrders.get(size-1).getDrinkPrice()+":"+FakeDBHelper.myOrders.get(size-1).getDrinkQuantity();
        history.insert(dateNow, order, calculateTotal(), FakeDBHelper.locationRestaurant);
    }

    @Override
    public void refreshView() {
        calculateTotal();
    }
}