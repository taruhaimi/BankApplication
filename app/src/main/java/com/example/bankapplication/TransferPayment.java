package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


public class TransferPayment extends AppCompatActivity {
    EditText accountID, moneyAmount;
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
    // this method transfers money between two existing accounts or between current account and "non-existing" account which can be for example account from another bank.
    public void pay(View v) {
        String id;
        int paymentIndexUser = 0; // index of the user from userlist which the account is going to be chosen from
        int paymentIndexAcc = 0; //index of the account from accountlist which the payment is going to be paid
        id = accountID.getText().toString();

        TransActions trans = new TransActions();
        try {
            money = Double.parseDouble(moneyAmount.getText().toString());


        } catch (NullPointerException | NumberFormatException e)  {
            Toast.makeText(context, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
        int flag = 0;

        try {
            //this loop goes through all users and all of the users' accounts through and tries to find the id which user has entered on edit text field.
            //i is the index for the user list and is saved to paymentIndexUser // j is the index for that specific users accounts list and is saved to paymentIndexAcc
            // if it finds an existing account it turns flag to 1
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
            // if there is existing account for the input it transfers money between these two accounts
            if (flag == 1) {
                if (MainActivity.accountArrayList().get(listIndex).getMoney() < money) {
                    Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.accountArrayList().get(listIndex).withdrawMoney(money);
                    MainActivity.userArrayList.get(paymentIndexUser).accountArrayList.get(paymentIndexAcc).depositMoney(money);
                    MainActivity.accountArrayList().get(listIndex).createTransaction(MainActivity.accountArrayList().get(listIndex).getID(),id,money, "Account transfer");
                    System.out.println("From "+MainActivity.accountArrayList().get(listIndex).getID()+" To "+MainActivity.userArrayList.get(paymentIndexUser).accountArrayList.get(paymentIndexAcc).getID());
                    Toast.makeText(this, "Transferred " + money + "€ from account " + MainActivity.accountArrayList().get(listIndex).getID() + " to account " + accountID.getText().toString() + " successfully.", Toast.LENGTH_LONG).show();
                }
            }
            // if there isnt existing account it only withdraws the money from the current account.
            else {
                MainActivity.accountArrayList().get(listIndex).withdrawMoney(money);
                MainActivity.accountArrayList().get(listIndex).createTransaction(MainActivity.accountArrayList().get(listIndex).getID(),id,money, "Account transfer");
                Toast.makeText(this, "Transferred " + money + "€ from account " + MainActivity.accountArrayList().get(listIndex).getID() + " to account " + accountID.getText().toString() + " successfully.", Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e)  {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
    }
}

