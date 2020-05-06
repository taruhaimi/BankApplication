package com.example.bankapplication;

import androidx.annotation.NonNull;
import java.util.Random;

public class Card {

    private String type, id, ContactlessPay, name, pincode, withdrawLimit, paymentLimit, creditLimit, regionLimitWithDraw, regionLimitPayment;
    private double withdrawAmount;

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void reduceWithdrawAmount(double withdrawReduce) {
        withdrawAmount = withdrawAmount - Math.abs(withdrawReduce);
        this.withdrawAmount = withdrawAmount;
    }
    public void setWithdrawAmount(double withdrawAmount)    {
        this.withdrawAmount = withdrawAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactlessPay() {
        return ContactlessPay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public String getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(String withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public String getPaymentLimit() {
        return paymentLimit;
    }

    public void setPaymentLimit(String paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getRegionLimitWithDraw() {
        return regionLimitWithDraw;
    }

    public void setRegionLimitWithDraw(String regionLimitWithDraw) {
        this.regionLimitWithDraw = regionLimitWithDraw;
    }

    public String getRegionLimitPayment() {
        return regionLimitPayment;
    }

    public void setRegionLimitPayment(String regionLimitPayment) {
        this.regionLimitPayment = regionLimitPayment;
    }

    public void createCard(String n, String t, String c, String pc) {
        Random rand = new Random();
        id = (rand.nextInt(8900)+1000)+" "+(rand.nextInt(8900)+1000)+" "+(rand.nextInt(8900)+1000)+" "+(rand.nextInt(8900)+1000);
        name = n;
        type = t;
        ContactlessPay = c;
        pincode = pc;
        withdrawLimit = "100";
        paymentLimit = "100";
        creditLimit = "0";
        regionLimitPayment = "0";
        regionLimitWithDraw = "0";
        withdrawAmount = 0;
    }

    @NonNull
    @Override
    public String toString() {
        return "Card Name: "+name+"\nCard id: "+id+"\nCard type: "+type+"\nContactless Payment: "+ContactlessPay;
    }

}
