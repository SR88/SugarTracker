package com.shneddy.sugartracker.entity;

public class Food {

    private int id;
    private String name;
    private double gramsSugar;
    private int foodTypeId;

    public Food() {
    }

    public Food(int id, String name, double gramsSugar, int foodTypeId) {
        this.id = id;
        this.name = name;
        this.gramsSugar = gramsSugar;
        this.foodTypeId = foodTypeId;
    }

    public Food(String name, double gramsSugar, int foodTypeId) {
        this.name = name;
        this.gramsSugar = gramsSugar;
        this.foodTypeId = foodTypeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGramsSugar() {
        return gramsSugar;
    }

    public void setGramsSugar(double gramsSugar) {
        this.gramsSugar = gramsSugar;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getName() {
        return name;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    @Override
    public String toString() {
        return "FoodC{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gramsSugar=" + gramsSugar +
                ", foodTypeId=" + foodTypeId +
                '}';
    }
}
