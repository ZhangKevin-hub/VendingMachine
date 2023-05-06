package com.example.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.List;
import com.example.vendingmachine.service.*;
import com.example.vendingmachine.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/vending-machine")
public class VendingMachineController {

    @Autowired
    private VendingMachineService vendingMachineService;

    private BigDecimal moneyBalance = BigDecimal.ZERO;

    @PostMapping("/add-money")
    public String addMoney(@RequestParam int amount) {
        moneyBalance = moneyBalance.add(BigDecimal.valueOf(amount));
        return "Money added successfully";
    }

    @GetMapping("/money")
    public BigDecimal viewMoney() {
        return moneyBalance;
    }
    
    @GetMapping("/return-money")
    public BigDecimal returnMoney() {
        BigDecimal balance = moneyBalance;
        moneyBalance = BigDecimal.ZERO;
        return balance;
    }

    @PostMapping("/purchase")
    public String purchaseItem(@RequestParam String slot) {
        try {
            Item item = vendingMachineService.getItemBySlot(slot);
            if (item.getQuantity() <= 0) {
                return "Item out of stock";
            }
            if (moneyBalance.compareTo(BigDecimal.valueOf(item.getCost())) < 0) {
                return "Not enough money to purchase";
            }
            moneyBalance = moneyBalance.subtract(BigDecimal.valueOf(item.getCost()));
            item.setQuantity(item.getQuantity() - 1);
            vendingMachineService.updateItem(item); // update item quantity in the database
            return "Item purchased successfully";
        } catch (ItemNotFoundException e) {
            return "Item not found";
        }
    }
    

    @GetMapping("/restock")
    public String restockItems() {
        try {
            List<Item> items = vendingMachineService.getAllItems();
            for (Item item : items) {
                item.setQuantity(4);
                vendingMachineService.updateItem(item);
            }
            return "Items restocked successfully";
        } catch (ItemNotFoundException e) {
            return "Error restocking items";
        }
    }

    @GetMapping("/items")
    public List<Item> getAllItems() {
        List<Item> items = vendingMachineService.getAllItems();
        return items;
    }

    @GetMapping("/items/{slot}")
    public Item getItemBySlot(@PathVariable String slot) throws ItemNotFoundException {
        return vendingMachineService.getItemBySlot(slot);
    }
}
