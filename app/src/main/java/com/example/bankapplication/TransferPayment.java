package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


public class TransferPayment extends AppCompatActivity {

    EditText accountID;
    EditText moneyAmount;
    int listIndex;
    double money;
    Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_payment);
        accountID = (EditText) findViewById(R.id.accNumber);
        moneyAmount = (EditText) findViewById((R.id.moneyAmount));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listIndex = extras.getInt("key");
        }
        final ArrayAdapter<Account> arrayAdapter = new ArrayAdapter<Account>(this,android.R.layout.simple_list_item_1,MainActivity.accountArrayList());
    }
    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }
    public void pay(View v) {
        try {
            money = Double.parseDouble(moneyAmount.getText().toString());
            if (MainActivity.accountArrayList().get(listIndex).getMoney() < money) {
                Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.accountArrayList().get(listIndex).withdrawMoney(money);
                Toast.makeText(this, "Transferred " + money + "€ from account " + MainActivity.accountArrayList().get(listIndex).getInformation() + " to account " + accountID.getText().toString() + " successfully.", Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
        catch (NullPointerException e)  {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
    }
}

