package com.example.uts_2201747975.model;

public class NearbyRestaurant implements Comparable  {

    private String name;
    private Double distance;

    public NearbyRestaurant(){}


    public NearbyRestaurant(String name, Double distance) {
        this.name = name;
        this.distance = distance;
    }


    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        Double compareDistance = ((NearbyRestaurant) o).getDistance();
        /* For Ascending order*/
        return (int) (this.distance-compareDistance);
    }

}
