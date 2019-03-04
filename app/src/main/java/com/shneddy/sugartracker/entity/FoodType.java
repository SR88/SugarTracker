package com.shneddy.sugartracker.entity;

public class FoodType {

    private int id;
    private String type;
    private String description;

    public FoodType() {
    }

    public FoodType(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public FoodType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "FoodTypeC{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
