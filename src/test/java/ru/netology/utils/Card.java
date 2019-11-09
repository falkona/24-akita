package ru.netology.utils;

import lombok.Data;

@Data
public class Card {
    private String number;
    private double balance;

    public static void transferMoneyCardToCard(Card cardFrom, Card cardTo, double amount) {
        cardFrom.setBalance(cardFrom.getBalance() - amount);
        cardTo.setBalance(cardTo.getBalance() + amount);
    }


}
