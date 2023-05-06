package com.example.vendingmachine.model;

import java.math.BigDecimal;

public abstract class Item {
    private String itemSlot;
    private String itemName;
    private double cost;
    private int quantity;
    
    public Item(String itemSlot, String itemName, double cost, int quantity) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.cost = cost;
        this.quantity = quantity;
    }
    public String getItemSlot() {
        return itemSlot;
    }

    public void setItemSlot(String itemSlot) {
        this.itemSlot = itemSlot;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getType(){
        return "Item";
    }
    // public String getMessage(){
    //     return "Item Out";
    // }
}
