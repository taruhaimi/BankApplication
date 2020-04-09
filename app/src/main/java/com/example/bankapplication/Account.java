package com.example.bankapplication;


import androidx.annotation.NonNull;

import java.util.ArrayList;


public abstract class Account {
    abstract double getMoney();

    abstract double depositMoney(double add);

    abstract double withdrawMoney(double remove);

    abstract String getInformation();

    abstract String getID();

    abstract String getType();

    abstract double getInterest();

    abstract void createCard(String n, String i, String c);

    ArrayList<Card> cardArrayList = new ArrayList<Card>();
}
class currentAccount extends Account	{
    double money;
    String name;
    String accountID;
    String type = "Current";

    @Override
    double getMoney() {
        return money;
    }

    @Override
    double depositMoney(double add) {
        money = money+add;
        return money;
    }

    @Override
    double withdrawMoney(double remove) {
        money = money-remove;
        return money;
    }

    @Override
    String getInformation() {
        return name;
    }

    @Override
    String getID() {
        return accountID;
    }

    @Override
    String getType() {
        return type;
    }

    @Override
    double getInterest() {
        return 0;
    }

    void createAccount(String n, String i, double m) {
        accountID = i;
        name = n;
        money = m;
    }
    void createCard(String n, String t, String c)   {
        Card card = new Card();
        card.createCard(n, t, c);
        cardArrayList.add(card);
    }

    @NonNull
    @Override
    public String toString() {
        String info;
        info = "Account name: "+name+"\nAccount-ID: " + accountID+"\nMoney: "+money+"\nType:"+type;
        return info;
    }
}


class savingAccount extends Account	{
    double money;
    String name;
    double interest = 1.02;
    String accountID;
    String type = "Saving";

    @Override
    double getMoney() {
    return money;
    }

    @Override
    double depositMoney(double add) {
        money = money+add;
        return money;
    }


    @Override
    String getInformation() {
        return name;
    }

    @Override
    String getID() {
        return accountID;
    }

    @Override
    String getType() {
        return type;
    }

    public double getInterest() {
        return interest;
    }

    @Override
    void createCard(String n, String t, String c)   {
            Card card = new Card();
            card.createCard(n, t, c);
            cardArrayList.add(card);
    }


    double withdrawMoney(double remove) {
        money = money-remove;
        return money;
    }

    void createAccount(String n, String i, double m) {
        name = n;
        money = m;
        accountID = i;
    }

    @NonNull
    @Override
    public String toString() {
        String info;
        info = "Account name: "+name+"\nAccount-ID: " + accountID+"\nMoney: "+money+"\nType: "+type;
        return info;
    }
}