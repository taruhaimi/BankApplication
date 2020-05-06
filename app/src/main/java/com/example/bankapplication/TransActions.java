package com.example.bankapplication;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class TransActions extends AppCompatActivity {

    int listIndex;
    Context context = null;
    TextView text;
    String who = "you";
    double amount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_actions);
        context = TransActions.this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listIndex = extras.getInt("key");
        }

        text = (TextView) findViewById(R.id.transACtView);

    }

    public void readCsv() {

    }

    public void writeCsv(int style, String who, double amount) {
        //ArrayList transactions = new ArrayList();


        /*if (style == 1) { //Payed money
            String info = "Recipient: " + who + "Amount: " + amount + "€";
            transactions.add(info);
        }
        else if (style == 2) { //Received money
            String info = "Payer: " + who + "Amount: " + amount + "€";
            transactions.add(info);
        }*/
    }
}

