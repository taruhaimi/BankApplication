package com.example.bankapplication;


import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Account {
    double money;
    String name;
    String accountID;
    String type;
    double interest;
    Date interestdate;
    String interestdate2;
    ArrayList<Card> cardArrayList = new ArrayList<Card>();
    //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public void setName(String name) {
        this.name = name;
    }

    public void setInterest(double interest) {
        this.interest = interest;
       // interestdate2 = formatter.format(interestdate);
    }

    public void setType(String type) {
        this.type = type;
    }

    double getMoney() {
        return money;
    }

    double depositMoney(double add) {
        money = money+add;
        return money;
    }

    double withdrawMoney(double remove) {
        money = money-remove;
        return money;
    }

    String getInformation() {
        return name;
    }

    String getID() {
        return accountID;
    }

    String getType() {
        return type;
    }

    double getInterest() {
        return interest;
    }

    void createAccount(String n, String i, double m, String t, double k) {
        accountID = i;
        name = n;
        money = m;
        type = t;
        interest = k;
    }
    void createCard(String n, String t, String c, String pc)   {
        Card card = new Card();
        card.createCard(n, t, c, pc);
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

/*
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
}*/
