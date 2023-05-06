package com.example.vendingmachine.repository;

import java.util.List;

import com.example.vendingmachine.model.Item;

public interface ItemRepository {
    List<Item> findAll();
    com.example.vendingmachine.model.Item findBySlot(String slot);
    void save(Item item);
    void update(Item item);
    void delete(Item item);
}
