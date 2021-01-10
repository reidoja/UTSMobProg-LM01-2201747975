package com.example.uts_2201747975;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.uts_2201747975.adapter.HistoryAdapter;
import com.example.uts_2201747975.adapter.NearbyAdapter;
import com.example.uts_2201747975.model.History;
import com.example.uts_2201747975.utils.HistoryDBHelper;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView historyRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyRv = findViewById(R.id.historyRv);

        HistoryDBHelper history = new HistoryDBHelper(this);

        ArrayList<History> histories = new ArrayList<>();


        int flag = 0;
        try {
            histories = history.getAll();
            flag = histories.size();
        } catch (Exception e) {
            flag = 0;
        }

        if(flag==0)return;

        RecyclerView.Adapter adapter = new HistoryAdapter(histories, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        historyRv.setLayoutManager(layoutManager);
        historyRv.setAdapter(adapter);

    }
}