package com.example.bankapplication;


import androidx.annotation.NonNull;

public abstract class Account {
    abstract void getMoney();
    abstract int depositMoney(int add);
    abstract int withdrawMoney(int remove);
    abstract String getInformation();
    abstract String getID();
    abstract String getType();
    abstract double getInterest();

}

class currentAccount extends Account	{
    int money;
    String name;
    String accountID;
    String type = "Current";
    @Override

    void getMoney() {
        System.out.println("Account number: "+name+" Amount of money: "+money);
    }

    @Override
    int depositMoney(int add) {
        money = money+add;
        return money;
    }

    @Override
    int withdrawMoney(int remove) {
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

    void createAccount(String n, String i, int m) {
        accountID = i;
        name = n;
        money = m;
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
    int money;
    String name;
    double interest = 0.02;
    String accountID;
    String type = "Saving";


    @Override
    void getMoney() {
    }

    @Override
    int depositMoney(int add) {
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
        return 0.02;
    }

    int withdrawMoney(int remove) {
        money = money-remove;
        return money;
    }

    void createAccount(String n, String i, int m ) {
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