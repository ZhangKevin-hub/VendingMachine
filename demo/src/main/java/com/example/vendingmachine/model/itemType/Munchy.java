package com.example.vendingmachine.model.itemType;

import com.example.vendingmachine.model.Item;

public class Munchy extends Item {
    public Munchy(String slot, String name, double price, int quantity) {
        super(slot, name, price, quantity);
    }
    // @Override
    // public String getMessage(){
    //     return "Munchy, Munchy, so Good!"+'\n';
    // }
    @Override
    public String getType(){
        return "Munchy";
    }
}