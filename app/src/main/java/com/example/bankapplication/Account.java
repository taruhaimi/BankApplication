package com.example.bankapplication;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Date;

public class Account {
    double money;
    String name, accountID, type; // type = current or savings
    double interest;
    private Date interestdate;

    ArrayList<Card> cardArrayList = new ArrayList<Card>();

    public void setName(String name) {
        this.name = name;
    }

    double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
        interestdate =  java.util.Calendar.getInstance().getTime();
    }

    public Date getInterestdate() {
        return interestdate;
    }

    public void setInterestdate(Date interestdate) {
        this.interestdate = interestdate;
    }

    String getType() {
        return type;
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

    void createAccount(String n, String i, double m, String t, double k) {
        accountID = i;
        name = n;
        money = m;
        type = t;
        interest = k;
        interestdate =  java.util.Calendar.getInstance().getTime();
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