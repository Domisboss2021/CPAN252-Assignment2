package com.szwec.database.model;

import java.io.Serializable;

public abstract class Inventory implements Serializable {

    private int id;
    private String name;
    private String sport;
    private int quantity;
    private double price;
    private String date;

    public Inventory(int id, String name, String sport, int quantity, double price, String date) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
