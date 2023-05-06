package com.example.vendingmachine.model.itemType;

import com.example.vendingmachine.model.Item;

public class Candy extends Item {
    public Candy(String slot, String name, double price, int quantity) {
        super(slot, name, price, quantity);
    }
    // @Override
    // public String getMessage(){
    //     return "Sugar, Sugar, so Sweet!"+'\n';
    // }
    @Override
    public String getType(){
        return "Candy";
    }
}