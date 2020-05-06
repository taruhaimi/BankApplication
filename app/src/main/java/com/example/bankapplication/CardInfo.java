package com.example.bankapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CardInfo extends AppCompatActivity {

    TextView cardName, cardNumber, connectedAccount, cardType, contactlessPay, pinCode, currentCredit;
    ListView cards;
    Button resetCredit;
    int listIndex, position;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        cardNumber = (TextView) findViewById(R.id.cardNumber);
        connectedAccount = (TextView) findViewById(R.id.connectedAccount);
        cardType =(TextView) findViewById(R.id.cardType);
        contactlessPay = (TextView) findViewById(R.id.contactlessPay);
        pinCode = (TextView) findViewById(R.id.pincode);
        cardName = (TextView) findViewById(R.id.cardName);
        cards = (ListView) findViewById(R.id.cardList);
        resetCredit = (Button) findViewById(R.id.resetCredit);
        currentCredit = (TextView) findViewById(R.id.currentCredit);
        Bundle extras = getIntent().getExtras();
        context = CardInfo.this;

        if (extras != null) {
            listIndex = extras.getInt("key"); // is the accountlists index for the current account in use.
            position = extras.getInt("key2"); // is the cardlists index for the current card in use.
            cardName.setText(MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getName());
            connectedAccount.setText("Connected to account: "+MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getId());
            pinCode.setText("Pin code: " + MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getPincode());
            cardType.setText("Card type: " + MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getType());
            contactlessPay.setText("Contactless payment: " + MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getContactlessPay());
            currentCredit.setText("Current credit: " + MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getWithdrawAmount());
        }
}
    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }

    public void cardSettings(View v) {
        Intent i = new Intent(CardInfo.this,EditCardSettings.class);
        i.putExtra("key", listIndex);
        i.putExtra("key2", position);
        startActivity(i);
    }
    //this method resets the credit which card has gathered.
    public void creditReset(View v) {
        MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).setWithdrawAmount(0);
        currentCredit.setText("Current credit: " + MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getWithdrawAmount());
        Toast.makeText(context, "Your credit has been reseted!", Toast.LENGTH_SHORT).show();
    }
    //this method deletes current card via confirmation window
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