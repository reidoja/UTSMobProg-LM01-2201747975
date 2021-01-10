package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TopUpActivity extends AppCompatActivity {

    String MyPref = "MyPrefs" ;
    EditText amountTxt;
    TextView balanceValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        init();

        SharedPreferences sp=getSharedPreferences(MyPref, MODE_PRIVATE);

        balanceValue.setText("Rp. " +sp.getInt("balance", 0));
    }

    private void init() {
        amountTxt = findViewById(R.id.amountTxt);
        balanceValue = findViewById(R.id.balanceValue);
    }

    public void myOrderClick(View view){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }



    public void topUpClick(View view){
        SharedPreferences.Editor edit=getSharedPreferences(MyPref, MODE_PRIVATE).edit();
        SharedPreferences sp=getSharedPreferences(MyPref, MODE_PRIVATE);
        String balanceText = amountTxt.getText().toString();
        Integer balance;
        try{
            balance = Integer.parseInt(balanceText);
        }catch(Exception e){
            balance = 0;
        }

        edit.putInt("balance", balance +sp.getInt("balance", 0));
        edit.commit();

        Toast.makeText(this,"Top Up Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}