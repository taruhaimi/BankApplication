package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLOutput;


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
        String id;
        int paymentIndexUser = 0; // index of the user from userlist which the account is going to be chosen from
        int paymentIndexAcc = 0; //index of the account from accountlist which the payment is going to be paid
        id = accountID.getText().toString();
        money = Double.parseDouble(moneyAmount.getText().toString());
        int flag = 0;
        try {
            for (int i=0;i<MainActivity.userArrayList.size();i++) {
                for (int j=0;j<MainActivity.userArrayList.get(i).accountArrayList.size();j++) {
                    if(id.equals(MainActivity.userArrayList.get(i).accountArrayList.get(j).getID()))    {
                        System.out.println(MainActivity.userArrayList.get(i).accountArrayList.get(j).getID());
                        flag = 1;
                        paymentIndexAcc = j;
                        paymentIndexUser = i;
                    }
                }
            }
            if (flag == 1) {
                if (MainActivity.accountArrayList().get(listIndex).getMoney() < money) {
                    Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.accountArrayList().get(listIndex).withdrawMoney(money);
                    MainActivity.userArrayList.get(paymentIndexUser).accountArrayList.get(paymentIndexAcc).depositMoney(money);
                    System.out.println("From "+MainActivity.accountArrayList().get(listIndex).getID()+" To "+MainActivity.userArrayList.get(paymentIndexUser).accountArrayList.get(paymentIndexAcc).getID());
                    Toast.makeText(this, "Transferred " + money + "€ from account " + MainActivity.accountArrayList().get(listIndex).getID() + " to account " + accountID.getText().toString() + " successfully.", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "The account you entered doesn't exist yet, try again.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
        catch (NullPointerException e)  {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
    }
}

