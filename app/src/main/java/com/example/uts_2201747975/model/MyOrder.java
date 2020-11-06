package com.example.uts_2201747975.model;

public class MyOrder {
    private String drinkName;
    private Integer drinkPrice;
    private Integer drinkQuantity;

    public MyOrder(String drinkName, Integer drinkPrice, Integer drinkQuantity) {
        this.drinkName = drinkName;
        this.drinkPrice = drinkPrice;
        this.drinkQuantity = drinkQuantity;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Integer getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(Integer drinkPrice) {
        this.drinkPrice = drinkPrice;
    }

    public Integer getDrinkQuantity() {
        return drinkQuantity;
    }

    public void setDrinkQuantity(Integer drinkQuantity) {
        this.drinkQuantity = drinkQuantity;
    }
}
