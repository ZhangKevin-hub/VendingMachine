package com.example.vendingmachine.model.itemType;

import com.example.vendingmachine.model.Item;

public class Drink extends Item {
    public Drink(String slot, String name, double price, int quantity) {
        super(slot, name, price, quantity);
    }
    // @Override
    // public String getMessage(){
    //     return "Drinky, Drinky, Slurp Slurp!"+'\n';
    // }
    @Override
    public String getType(){
        return "Drink";
    }
}