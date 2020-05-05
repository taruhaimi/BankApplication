package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class CardPayment extends AppCompatActivity {
    ListView cards;
    EditText moneyAmount, pinCode;
    double money;
    String pin = "";
    Spinner regions;
    int index;

    ArrayList<Card> allCards = new ArrayList<Card>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);
        moneyAmount = (EditText) findViewById(R.id.moneyAmount);
        pinCode = (EditText) findViewById(R.id.getPinCode);
        cards = (ListView) findViewById(R.id.cardView);
        regions = (Spinner) findViewById(R.id.countrySpinner);

        for (int i = 0; i < MainActivity.userArrayList.get(MainActivity.currentIndex).accountArrayList.size(); i++) {
            allCards.addAll(MainActivity.userArrayList.get(MainActivity.currentIndex).accountArrayList.get(i).cardArrayList);
        }
        ArrayAdapter<Card> arrayAdapter = new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_1, allCards);
        cards.setAdapter(arrayAdapter);
        cards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 index = position;
             }
         });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, EditCardSettings.categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regions.setAdapter(dataAdapter);

    }

    public void cardPay(View v) {

        try {
            String country = regions.getSelectedItem().toString();
            money = Double.parseDouble(moneyAmount.getText().toString().trim());
            pin = pinCode.getText().toString().trim();

            for (int i = 0; i < MainActivity.accountArrayList().size(); i++) {

                if (MainActivity.accountArrayList().get(i).cardArrayList.contains(allCards.get(index))) {

                    if (allCards.get(index).getContactlessPay().equals("No") && pin.equals("")) {
                        Toast.makeText(this, "Your card does not have contactless payment. Please give pin code.", Toast.LENGTH_SHORT).show();
                    } else if (MainActivity.accountArrayList().get(i).getMoney() < money) {
                        Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show();
                    } else if (pin.equals("") && money <= 50) {
                        MainActivity.accountArrayList().get(i).withdrawMoney(money);
                        Toast.makeText(this, "Paid " + money + "€ from account " + MainActivity.accountArrayList().get(i).getInformation() + " successfully.", Toast.LENGTH_LONG).show();
                        break;
                    } else if (pin.equals("") && money > 50) {
                        Toast.makeText(this, "Money amount is over 50€. Please give pin code.", Toast.LENGTH_SHORT).show();
                    } else if (!pin.equals(allCards.get(index).getPincode())) {
                        Toast.makeText(this, "Pin code is wrong. Try again.", Toast.LENGTH_LONG).show();
                    } else {
                        MainActivity.accountArrayList().get(i).withdrawMoney(money);
                        Toast.makeText(this, "Paid " + money + "€ from account " + MainActivity.accountArrayList().get(i).getInformation() + " successfully.", Toast.LENGTH_LONG).show();
                    }
                }
            }

        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e)  {
            Toast.makeText(this, "Field can't be empty, try again!", Toast.LENGTH_SHORT).show();
        }


    }
}
