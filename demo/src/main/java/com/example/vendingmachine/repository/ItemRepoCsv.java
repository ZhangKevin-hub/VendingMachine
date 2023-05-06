package com.example.vendingmachine.repository;

import com.example.vendingmachine.model.Item;
import com.example.vendingmachine.model.itemType.Candy;
import com.example.vendingmachine.model.itemType.Drink;
import com.example.vendingmachine.model.itemType.Gum;
import com.example.vendingmachine.model.itemType.Munchy;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ItemRepoCsv implements ItemRepository {

    private static final String CSV_FILE_PATH = "./demo/src/main/resources/data.csv";

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String itemSlot = nextLine[0];
                String itemName = nextLine[1];
                double cost = Double.parseDouble(nextLine[2]);
                String itemType = nextLine[3];
                int quantity = Integer.parseInt(nextLine[4]);
                Item item = null;
                switch (itemType) {
                    case "Candy":
                        item = new Candy(itemSlot, itemName, cost, quantity);
                        break;
                    case "Drink":
                        item = new Drink(itemSlot, itemName, cost, quantity);
                        break;
                    case "Gum":
                        item = new Gum(itemSlot, itemName, cost, quantity);
                        break;
                    case "Munchy":
                        item = new Munchy(itemSlot, itemName, cost, quantity);
                        break;
                }
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        for (Item item : items) {
            System.out.println(item);
        }
        return items;
    }
    
    @Override
    public Item findBySlot(String slot) throws ItemNotFoundException {
        List<Item> items = findAll();
        for (Item item : items) {
            if (item.getItemSlot().equals(slot)) {
                return item;
            }
        }
        throw new ItemNotFoundException("Item with slot " + slot + " not found.");
    }

    @Override
    public void update(Item item) throws ItemNotFoundException {
        List<Item> items = findAll();
        boolean itemFound = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemSlot().equals(item.getItemSlot())) {
                items.set(i, item);
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            throw new ItemNotFoundException("Item not found with slot id: " + item.getItemSlot());
        }
        saveAll(items);
    }

    @Override
    public void save(Item item) {
        List<Item> items = findAll();
        items.add(item);
    }
    @Override
    public void delete(Item item) throws ItemNotFoundException {
        List<Item> items = findAll();
        if (!items.remove(item)) {
            throw new ItemNotFoundException("Item with id " + item.getItemSlot() + " not found");
        }
    }
    private void saveAll(List<Item> items) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH))) {
            for (Item item : items) {
                String[] line = {item.getItemSlot(), item.getItemName(), Double.toString(item.getCost()), item.getType(),Integer.toString(item.getQuantity())};
                writer.writeNext(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


