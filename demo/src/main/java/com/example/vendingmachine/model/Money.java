package com.example.vendingmachine.model;
import java.math.BigDecimal;

public class Money {
    private static BigDecimal balance = BigDecimal.ZERO;

    public static BigDecimal getBalance() {
        return balance;
    }

    public static void addMoney(BigDecimal moneyToAdd) {
        balance = balance.add(moneyToAdd);
    }

    public static void subtractMoney(BigDecimal moneyToSubtract) {
        balance = balance.subtract(moneyToSubtract);
    }

    public static void resetBalance() {
        balance = BigDecimal.ZERO;
    }
}
