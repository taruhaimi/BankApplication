package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CardPayment extends AppCompatActivity {
    int listIndex;
    int listposition;
    ListView cards;
    EditText moneyAmount;
    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);
        moneyAmount = (EditText) findViewById(R.id.moneyAmount);
        /*
        Bundle extras = getIntent().getExtras();
        cards = (ListView) findViewById(R.id.cardList);
        if (extras != null) {
            listIndex = extras.getInt("key");
        }
        final ArrayAdapter<Card> arrayAdapter = new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_1, MainActivity.accountArrayList.get(listIndex).cardArrayList);
        cards.setAdapter(arrayAdapter);
        cards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listposition = position;

            }
        });

         */

    }
    /*
    public void AccountPay(View v)  {
        try {
            moneyAmount = Double.parseDouble(moneyAmount.getText().toString());
            if (MainActivity.accountArrayList.get(listIndex).getMoney() < money) {
                Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.accountArrayList.get(listIndex).withdrawMoney(money);
                Toast.makeText(this, "Transferred " + moneyAmount + "€ from account " + MainActivity.accountArrayList.get(listIndex).getInformation() + " to account " + MainActivity.accountArrayList.get(listposition).getInformation() + "successfully.", Toast.LENGTH_LONG).show();

            }
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
        catch (NullPointerException e)  {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
    }

     */
}
