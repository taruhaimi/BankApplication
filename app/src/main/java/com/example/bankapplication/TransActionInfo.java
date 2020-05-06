package com.example.bankapplication;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransActionInfo {

    private String who;
    private double amount;
    private String from;
    private String info;
    private String transactiontype;
    private Date currentdate;
    int type;
    void saveInfo(String f, String w, double a, String t) {
        from = f;
        who = w;
        amount = a;
        transactiontype = t;
        currentdate = new Date();
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
        info = "From account: " + from + "\nTo: " + who + "\nAmount: " + amount+"\nType: "+ transactiontype+"\nDate: "+currentdate;
        return info;
    }
}

