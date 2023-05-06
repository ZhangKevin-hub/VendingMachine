package com.example.vendingmachine.model.itemType;

import com.example.vendingmachine.model.Item;

public class Gum extends Item {
    public Gum(String slot, String name, double price, int quantity) {
        super(slot, name, price, quantity);
    }
    // @Override
    // public String getMessage(){
    //     return "Chewy, Chewy, Lots O Bubbles!"+'\n';
    // }
    @Override
    public String getType(){
        return "Gum";
    }
}