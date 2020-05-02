package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class AddCard extends AppCompatActivity {
    EditText cardName;
    Switch contactlessSwitch;
    Switch typeSwitch;
    Spinner accountList;
    String Name;
    String contactless;
    String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        cardName = (EditText) findViewById(R.id.giveCardName);
        contactlessSwitch = (Switch) findViewById(R.id.choosePayment);
        typeSwitch = (Switch) findViewById(R.id.chooseType);
        accountList = (Spinner) findViewById(R.id.accountSpinner);
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(this, android.R.layout.simple_spinner_item, MainActivity.accountArrayList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountList.setAdapter(adapter);
    }
    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }

public void createCard(View v)  {
        int position;
        try {
            Name = cardName.getText().toString();
            boolean switchContact = contactlessSwitch.isChecked();
            if (!switchContact)  {
                contactless = "No";
            }
            else if (switchContact)    {
                contactless = "Yes";
            }
            boolean switchType = typeSwitch.isChecked();
            if (!switchType)  {
                Type = "Debit";
            }
            else if (switchType)    {
                Type = "Credit";
            }
            position = accountList.getSelectedItemPosition();
            MainActivity.accountArrayList().get(position).createCard(Name, Type, contactless);
            Toast.makeText(this, "Created card: "+Name, Toast.LENGTH_SHORT).show();
        } finally {
        }
        }
}