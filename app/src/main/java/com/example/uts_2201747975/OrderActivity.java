package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uts_2201747975.model.MyOrder;
import com.example.uts_2201747975.utils.FakeDBHelper;

public class OrderActivity extends AppCompatActivity {

    TextView nameLbl, priceLbl;
    EditText quantityTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        String drinkName = getIntent().getStringExtra("DRINKNAME");
        String drinkPrice = getIntent().getStringExtra("DRINKPRICE");

        init();

        nameLbl.setText(drinkName);
        priceLbl.setText("Rp. " +drinkPrice);

    }

    private void init() {
        nameLbl = findViewById(R.id.nameLbl);
        priceLbl = findViewById(R.id.priceLbl);
        quantityTxt = findViewById(R.id.quantityTxt);
    }

    public void myOrderClick(View view){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }

    public void orderMoreClick(View view){
        Intent intent = new Intent(this, DrinkActivity.class);
        startActivity(intent);
    }

    public void addOrderClick(View view){
        String nameText = nameLbl.getText().toString();
        String[] priceText = priceLbl.getText().toString().split(" ");
        String quantityText = quantityTxt.getText().toString();

        Integer price = Integer.parseInt(priceText[1]);
        Integer quantity;
        try{
            quantity = Integer.parseInt(quantityText);
        }catch(Exception e){
            quantity = 0;
        }

        if(quantity == 0) {
            Toast.makeText(this, "Please Input Quantity!", Toast.LENGTH_SHORT).show();
        }
        else{
            FakeDBHelper.myOrders.add(new MyOrder(nameText, price, quantity));
            Toast.makeText(this, "Item Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
}