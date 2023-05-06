package com.example.vendingmachine.service;

import com.example.vendingmachine.model.Item;
import com.example.vendingmachine.repository.ItemRepoCsv;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VendingMachineService implements VendingMachine{

    private ItemRepoCsv itemRepoCsv;

    public VendingMachineService(ItemRepoCsv itemRepoCsv) {
        this.itemRepoCsv = itemRepoCsv;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepoCsv.findAll();
    }

    @Override
    public Item getItemBySlot(String slot) throws ItemNotFoundException {
        return itemRepoCsv.findBySlot(slot);
    }

    @Override
    public void updateItem(Item item) throws ItemNotFoundException {
        itemRepoCsv.update(item);
    }

    @Override
    public void addItem(Item item) {
        itemRepoCsv.save(item);
    }

    @Override
    public void deleteItem(Item item) throws ItemNotFoundException {
        itemRepoCsv.delete(item);
    }

}
