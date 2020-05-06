package com.example.bankapplication;

import androidx.annotation.NonNull;

public class TransActionInfo {

    private String who;
    private double amount;
    private String from;
    public String info;
    public String transactiontype;
    int type;

    public void saveInfo(String f, String w, double a, String t) {
        from = f;
        who = w;
        amount = a;
        transactiontype = t;
    }

    public String toInfoString() {
        if (type == 1) { //Payed money
            info = "From account: " + from + "\nTo: " + who + "\nAmount: " + amount;
        }
        else if (type == 2) { //Received money
             info = "From: " + who + "\nTo: Me" + "\nAmount: " + amount;
        }
        return info;
    }

    @NonNull
    @Override
    public String toString() {
        info = "From account: " + from + "\nTo: " + who + "\nAmount: " + amount+"\n Type: "+ transactiontype;
        return info;
    }
}

