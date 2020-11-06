package com.example.uts_2201747975.utils;

import com.example.uts_2201747975.R;
import com.example.uts_2201747975.model.Drink;
import com.example.uts_2201747975.model.MyOrder;

import java.util.ArrayList;

public class FakeDBHelper {

    public static ArrayList<MyOrder> myOrders = new ArrayList<>();

    String[] drinks = {"Air Mineral", "Jus Jeruk", "Es Teh Manis", "Es Teh Tawar", "Jus Jambu", "Vanilla MilkShake", "Coca-Cola", "Sprite", "Lemon Tea"};
    Integer[] prices = {5000, 10000, 4000, 3000, 10000, 15000, 8000, 8000, 10000};
    Integer[] images = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8};

    public ArrayList<Drink> getAll() {
        ArrayList<Drink> list = new ArrayList<>();
        Drink d;
        int length = drinks.length;
        for(int i = 0 ; i < length ; i ++ ){
            d = new Drink(drinks[i], prices[i], images[i]);
            list.add(d);
        }

        return list;

    }

}
