package com.example.bankapplication;

public class TransActionInfo {

    private String who;
    private String amount;
    private String from;
    public String info;
    int type;

    public void saveInfo(String f, String w, String a) {
        from = f;
        who = w;
        amount = a;
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
}

