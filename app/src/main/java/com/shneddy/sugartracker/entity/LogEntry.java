package com.shneddy.sugartracker.entity;

public class LogEntry {

    private int id;
    private int foodId;
    private double portionSize;
    private String date;

    public LogEntry() {
    }

    public LogEntry(int id, int foodId, double portionSize, String date) {
        this.id = id;
        this.foodId = foodId;
        this.portionSize = portionSize;
        this.date = date;
    }

    public LogEntry(int foodId, double portionSize, String date) {
        this.foodId = foodId;
        this.portionSize = portionSize;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public double getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(double portionSize) {
        this.portionSize = portionSize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LogEntryC{" +
                "id=" + id +
                ", foodId=" + foodId +
                ", portionSize=" + portionSize +
                ", date='" + date + '\'' +
                '}';
    }
}
