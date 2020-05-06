package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class CardPayment extends AppCompatActivity {
    ListView cards;
    EditText moneyAmount, pinCode;
    double money;
    String pin = "", regionLoc, cardRegion;
    Spinner regions;
    int index;
    ArrayList<Card> allCards = new ArrayList<Card>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

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

    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }

    // This method takes care of all the conditional expressions that must be checked before user can pay with their card and if everything is fine they can pay.
    public void cardPay(View v) {

        try {
            String country = regions.getSelectedItem().toString();
            money = Double.parseDouble(moneyAmount.getText().toString().trim());
            pin = pinCode.getText().toString().trim();
            regionLoc = Integer.toString(regions.getSelectedItemPosition());
            double withdrawCheck; //will be used to check how much accounts balance will go below zero (you can compare this and cards current credit with cards creditlimit).

            // This loop searches user's account and card. Then it checks all the possible things that can go wrong when user tries to pay with card (e.g they have not enough money).
            for (int i = 0; i < MainActivity.accountArrayList().size(); i++) {

                if (MainActivity.accountArrayList().get(i).cardArrayList.contains(allCards.get(index))) {
                    cardRegion = allCards.get(index).getRegionLimitPayment();

                    if (allCards.get(index).getContactlessPay().equals("No") && pin.equals("")) {
                        Toast.makeText(this, "Your card does not have contactless payment. Please give pin code.", Toast.LENGTH_SHORT).show();
                    } else if (!(allCards.get(index).getRegionLimitPayment().equals(regionLoc))) {
                        Toast.makeText(this, "You can not pay with this card at this region. Please change region.", Toast.LENGTH_SHORT).show();
                    } else if (MainActivity.accountArrayList().get(i).getMoney() + Double.parseDouble(allCards.get(index).getCreditLimit()) < money) {
                        Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(allCards.get(index).getPaymentLimit()) < money) {
                        Toast.makeText(this, "Money amount is above your payment limit, sorry.", Toast.LENGTH_SHORT).show();
                    } else if (pin.equals("") && money <= 50) {
                        MainActivity.accountArrayList().get(i).withdrawMoney(money);
                        Toast.makeText(this, "Paid " + money + "€ from account " + MainActivity.accountArrayList().get(i).getInformation() + " successfully.", Toast.LENGTH_LONG).show();
                        break;
                    } else if (pin.equals("") && money > 50) {
                        Toast.makeText(this, "Money amount is over 50€. Please give pin code.", Toast.LENGTH_SHORT).show();
                    } else if (!pin.equals(allCards.get(index).getPincode())) {
                        Toast.makeText(this, "Pin code is wrong. Try again.", Toast.LENGTH_LONG).show();
                    } else {
                        withdrawCheck = MainActivity.accountArrayList().get(i).getMoney() - money;
                        System.out.println(Math.abs(allCards.get(index).getWithdrawAmount() - withdrawCheck));
                        System.out.println(withdrawCheck);
                        if (withdrawCheck < 0 && allCards.get(index).getType().equals("Credit")) {
                            if (Double.parseDouble(allCards.get(index).getCreditLimit()) >= Math.abs(allCards.get(index).getWithdrawAmount() + withdrawCheck)) {
                                allCards.get(index).reduceWithdrawAmount(withdrawCheck);
                                MainActivity.accountArrayList().get(i).withdrawMoney(MainActivity.accountArrayList().get(i).getMoney());
                                Toast.makeText(this, "Paid " + money + "€ from account " + MainActivity.accountArrayList().get(i).getInformation() + " successfully.\nYour credit is currently "+allCards.get(index).getWithdrawAmount(), Toast.LENGTH_LONG).show();
                                System.out.println(allCards.get(index).getWithdrawAmount());
                            }
                            else {
                                Toast.makeText(this, "You do not have enough credit for this payment!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                                MainActivity.accountArrayList().get(i).withdrawMoney(money);
                                Toast.makeText(this, "Paid " + money + "€ from account " + MainActivity.accountArrayList().get(i).getInformation() + " successfully.", Toast.LENGTH_LONG).show();
                            }
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
