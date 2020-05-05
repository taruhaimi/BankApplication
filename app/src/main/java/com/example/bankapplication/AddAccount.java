package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Random;

public class AddAccount extends AppCompatActivity {
    String accountname;
    String id;
    EditText accName;
    Switch accType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        accName = (EditText) findViewById(R.id.accName);
        accType = (Switch) findViewById(R.id.accTypeChooser);
    }

    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }

    public void chooseType(View v) {
        boolean switchstate = accType.isChecked();
        if (!switchstate)  {
            createCurrentAccount(v);
        }
        if (switchstate)    {
            createSavingsAccount(v);
        }
    }

    public void createCurrentAccount (View v)    {
        Random rand = new Random();
        accountname = accName.getText().toString();
        id = (rand.nextInt(900)+100)+"-"+(rand.nextInt(900)+100)+"-"+(rand.nextInt(900)+100);
        Account account = new Account();
        int flag = 0;
        for (int i=0; i<MainActivity.accountArrayList().size(); i++)  {
            if (MainActivity.accountArrayList().get(i).getInformation().equals(accountname))   {
                Toast.makeText(this, "Account name: "+accountname+" is already taken.", Toast.LENGTH_SHORT).show();
                flag = 1;
            }
            if (MainActivity.accountArrayList().get(i).getID().equals(id))   {
                Toast.makeText(this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                flag = 1;
            }
        }

        if (flag == 0)  {
        account.createAccount(accountname, id, 0, "Current", 1);
        MainActivity.accountArrayList().add(account);
        Toast.makeText(this, "Created account: "+accountname+"\nId: "+id, Toast.LENGTH_SHORT).show();

        }
    }

    public void createSavingsAccount (View v)    {
        Random rand = new Random();
        accountname = accName.getText().toString();
        id = (rand.nextInt(900)+100)+"-"+(rand.nextInt(900)+100)+"-"+(rand.nextInt(900)+100);
        Account account = new Account();
        int flag = 0;
        for (int i=0; i<MainActivity.accountArrayList().size(); i++)  {
            if (MainActivity.accountArrayList().get(i).getInformation().equals(accountname))   {
                Toast.makeText(this, "Account name: "+accountname+"is already taken.", Toast.LENGTH_SHORT).show();
                flag = 1;
            }
            if (MainActivity.accountArrayList().get(i).getID().equals(id))   {
                Toast.makeText(this, "Your account number is already taken, try again", Toast.LENGTH_SHORT).show();
                flag = 1;
            }
        }
        if (flag == 0)  {
            account.createAccount(accountname, id, 0, "Savings", 1.02);
            MainActivity.accountArrayList().add(account);
            Toast.makeText(this, "Created account: "+accountname+"\nId: "+id, Toast.LENGTH_SHORT).show();
        }
    }

}
