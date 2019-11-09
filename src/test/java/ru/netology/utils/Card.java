package ru.netology.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    private String id;
    private String number;
    private double balance;
    private String lastSymbols;

    public static void transferMoneyCardToCard(Card cardFrom, Card cardTo, double amount) {
        cardFrom.setBalance(cardFrom.getBalance() - amount);
        cardTo.setBalance(cardTo.getBalance() + amount);
    }
}
