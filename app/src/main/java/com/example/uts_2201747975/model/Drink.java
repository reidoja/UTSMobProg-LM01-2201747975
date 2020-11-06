package com.example.uts_2201747975.model;

public class Drink {
    private String name;
    private Integer price;
    private Integer image;

    public Drink(String name, Integer price, Integer image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
