package com.example.bankapplication;


import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.Date;


public class Account {
    double money;
    String name;
    String accountID;
    String type;
    double interest;



    Date interestdate;
    ArrayList<Card> cardArrayList = new ArrayList<Card>();
    ArrayList<TransActionInfo> transActionInfoArrayList = new ArrayList<TransActionInfo>();
    public void setName(String name) {
        this.name = name;
    }

    public void setInterest(double interest) {
        this.interest = interest;
        interestdate =  java.util.Calendar.getInstance().getTime(); // get date and time of last interest
    }
    public Date getInterestdate() {
        return interestdate;
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
        interestdate =  java.util.Calendar.getInstance().getTime();
    }
    void createCard(String n, String t, String c, String pc)   {
        Card card = new Card();
        card.createCard(n, t, c, pc);
        cardArrayList.add(card);
    }

    void createTransaction(String f, String w, double a, String t)    {
        TransActionInfo info = new TransActionInfo();
        info.saveInfo(f,w,a, t);
        transActionInfoArrayList.add(info);
    }

    public void setInterestdate(Date interestdate) {
        this.interestdate = interestdate;
    }

    String getRealInterest() {
        return String.format("%1.02f",((interest)-1)*100);
    }
    @NonNull
    @Override
    public String toString() {
        String info;
        info = "Account name: "+name+"\nAccount-ID: " + accountID+"\nMoney: "+String.format("%1.2f",money)+"\nType:"+type;
        return info;
    }
}