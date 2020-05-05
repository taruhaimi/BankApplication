package com.example.bankapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CardInfo extends AppCompatActivity {

    EditText cardName;
    TextView cardNumber, connectedAccount, cardType, cardExpirationDate, contactlessPay, pinCode;
    ListView cards;
    int listIndex;
    int position;
    Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);
        cardNumber = (TextView) findViewById(R.id.cardNumber);
        connectedAccount = (TextView) findViewById(R.id.connectedAccount);
        cardType =(TextView) findViewById(R.id.cardType);
        cardExpirationDate = (TextView) findViewById(R.id.cardExpirationDate);
        contactlessPay = (TextView) findViewById(R.id.contactlessPay);
        pinCode = (TextView) findViewById(R.id.pincode);
        cardName = (EditText) findViewById(R.id.cardName);
        cards = (ListView) findViewById(R.id.cardList);
        Bundle extras = getIntent().getExtras();
        context = CardInfo.this;

        if (extras != null) {
            listIndex = extras.getInt("key");
            position = extras.getInt("key2");
            System.out.println(listIndex);
            Card card = new Card();
            cardName.setText(MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getName());
            connectedAccount.setText("Connected to account: "+MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getId());
            pinCode.setText("Pin code: " + MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getPincode());
            cardType.setText("Card type: " + MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getType());

            cardExpirationDate.setText("Expiration date: " + android.text.format.DateFormat.format("MM/yy", new java.util.Date()));
            //contactlessPay.setText("Contactless pay option: "+Card.getContactlessPay());
            contactlessPay.setText("Contactless payment: " + MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getContactlessPay());

        }
}

    public void cardSettings(View v) {startActivity(new Intent(CardInfo.this,EditCardSettings.class)); }


    public void deleteCard(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete Card");
        builder.setMessage("Are you sure you want to delete this card?\nPress cancel to cancel.");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.accountArrayList().get(listIndex).cardArrayList.remove(position);
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}