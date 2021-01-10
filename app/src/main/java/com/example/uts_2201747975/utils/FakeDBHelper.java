package com.example.uts_2201747975.utils;

import com.example.uts_2201747975.R;
import com.example.uts_2201747975.model.Drink;
import com.example.uts_2201747975.model.MyOrder;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FakeDBHelper {

    public static ArrayList<MyOrder> myOrders = new ArrayList<>();
    public static ArrayList<LatLng> latLngs = new ArrayList<>();
    public static ArrayList<String> title = new ArrayList<>();
    public static String locationRestaurant = "";
    public static int choose = 0;

    String[] drinks = {"Air Mineral", "Jus Jeruk", "Es Teh Manis", "Es Teh Tawar", "Jus Jambu", "Vanilla MilkShake", "Coca-Cola", "Sprite", "Lemon Tea"};
    String[] snacks = {"Chitato", "Lays", "Chip"};
    String[] foods = {"Nasi Goreng", "Ayam Goreng"};


    Integer[] prices = {5000, 10000, 4000, 3000, 10000, 15000, 8000, 8000, 10000};
    Integer[] prices1 = {6000, 7000, 5000};
    Integer[] prices2 = {15000, 12000};

    Integer[] images = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8};
    Integer[] images1 = {R.drawable.snacks1, R.drawable.snacks2, R.drawable.snacks};
    Integer[] images2 = {R.drawable.food1, R.drawable.food2};


    static LatLng alamSutera = new LatLng(-6.223358, 106.649234);
    static LatLng anggrek = new LatLng(-6.201723, 106.780984);
    static LatLng malang = new LatLng(-7.939973, 112.681161);
    static LatLng bekasi = new LatLng(-6.220145, 106.999709);
    static LatLng serpong = new LatLng(-6.277450, 106.666691);

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

    public ArrayList<Drink> getAll1() {
        ArrayList<Drink> list = new ArrayList<>();
        Drink d;
        int length = snacks.length;
        for(int i = 0 ; i < length ; i ++ ){
            d = new Drink(snacks[i], prices1[i], images1[i]);
            list.add(d);
        }
        return list;
    }

    public ArrayList<Drink> getAll2() {
        ArrayList<Drink> list = new ArrayList<>();
        Drink d;
        int length = foods.length;
        for(int i = 0 ; i < length ; i ++ ){
            d = new Drink(foods[i], prices2[i], images2[i]);
            list.add(d);
        }
        return list;
    }

    public static void initLocation(){

        latLngs.removeAll(latLngs);
        title.removeAll(title);

        latLngs.add(alamSutera);
        latLngs.add(anggrek);
        latLngs.add(malang);
        latLngs.add(bekasi);
        latLngs.add(serpong);

        title.add("Alam Sutera");
        title.add("Anggrek");
        title.add("Malang");
        title.add("Bekasi");
        title.add("Serpong");

    }


}
