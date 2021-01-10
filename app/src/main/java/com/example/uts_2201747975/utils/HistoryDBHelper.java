package com.example.uts_2201747975.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uts_2201747975.model.History;
import com.example.uts_2201747975.model.MyOrder;

import java.util.ArrayList;

public class HistoryDBHelper extends SQLiteOpenHelper {

    private static String CREATE_HISTORY_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS History(" +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Date TEXT," +
            "Transactions TEXT," +
            "Location TEXT," +
            "Total INTEGER)";

    private static String DROP_HISTORY_TABLE_QUERY = "DROP TABLE IF EXISTS History";

    public HistoryDBHelper(Context ctx){
        super(ctx, "ezyfood", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HISTORY_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_HISTORY_TABLE_QUERY);
        db.execSQL(CREATE_HISTORY_TABLE_QUERY);
    }

    public void insert(String date, String transaction, Integer total, String location){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Date", date);
        cv.put("Transactions", transaction);
        cv.put("Total", total);
        cv.put("Location", location);
        db.insert("History", null, cv);
        db.close();
    }

    public void clearDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        String clearDBQuery = "DELETE FROM " + "History";
        db.execSQL(clearDBQuery);
    }


    public ArrayList<History> getAll() throws Exception{
        ArrayList<History> list = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM HISTORY", null);

        if(c.moveToNext()){
            list = new ArrayList<>();
            c.moveToPrevious();
            while(c.moveToNext()){
                History h = new History();
                h.setId(c.getInt(0));
                h.setDate(c.getString(1));
                String[] transactions = c.getString(2).split(",");
                ArrayList<MyOrder> transactionList = new ArrayList<>();
                for(int i = 0 ; i <transactions.length ; i ++){
                    String[] transaction = transactions[i].split(":");
                    MyOrder mo = new MyOrder(transaction[0],Integer.parseInt(transaction[1]), Integer.parseInt(transaction[2]));
                    transactionList.add(mo);
                }
                h.setTransaction(transactionList);
                h.setLocation(c.getString(3));
                h.setTotal(c.getInt(4));
                list.add(h);
            }
        }
        c.close();
        return list;
    }
}
