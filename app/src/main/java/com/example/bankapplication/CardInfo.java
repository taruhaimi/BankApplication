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
    TextView cardNumber;
    TextView connectedAccount;
    TextView cardType;
    TextView cardExpirationDate;
    TextView contactlessPay;
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
        cardName = (EditText) findViewById(R.id.cardName);
        cards = (ListView) findViewById(R.id.cardList);
        Bundle extras = getIntent().getExtras();
        context = CardInfo.this;

        if (extras != null) {
            listIndex = extras.getInt("key");
            System.out.println(listIndex);
            Card card = new Card();
            cardName.setText(card.getName()); //TODO: kortin nimi
            connectedAccount.setText("Connected to account: "+MainActivity.accountArrayList().get(listIndex).getID());
            cardType.setText("" + MainActivity.accountArrayList().get(listIndex).cardArrayList); // TODO: kortin tyyppi

            cardExpirationDate.setText("Expiration date: " + android.text.format.DateFormat.format("MM/yy", new java.util.Date()));
            //contactlessPay.setText("Contactless pay option: "+Card.getContactlessPay());

        }
}
    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
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
                        //MainActivity.accountArrayList.remove(listIndex);
                        //ArrayAdapter<Card> arrayAdapter = new ArrayAdapter<Card>(this,android.R.layout.simple_list_item_1,MainActivity.accountArrayList.get(listIndex).cardArrayList);
                        //cards.setAdapter(arrayAdapter);
                        //MainActivity.accountArrayList.remove(listIndex).cardArrayList;
                        //position = MainActivity.accountArrayList.indexOf(listIndex);
                        //position = accountList.getSelectedItemPosition();
                        //MainActivity.accountArrayList.get(position).cardArrayList.remove();
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