package com.example.vendingmachine.service;

import java.util.List;

import com.example.vendingmachine.model.Item;

public interface VendingMachine {
    List<Item> getAllItems();
    Item getItemBySlot(String slot) throws ItemNotFoundException;

    void updateItem(Item item) throws ItemNotFoundException;

    void addItem(Item item);

    void deleteItem(Item item) throws ItemNotFoundException;
}