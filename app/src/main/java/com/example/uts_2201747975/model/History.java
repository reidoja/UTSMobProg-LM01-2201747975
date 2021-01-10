package com.example.uts_2201747975.model;

import java.util.ArrayList;

public class History {
    private Integer id;
    private String date;
    private ArrayList<MyOrder> transaction;
    private String location;
    private Integer total;


    public History() {
    }
    public ArrayList<MyOrder> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<MyOrder> transaction) {
        this.transaction = transaction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
