package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

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
        contactlessSwitch = (Switch) findViewById(R.id.switch3);
        typeSwitch = (Switch) findViewById(R.id.switch1);
        accountList = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(this, android.R.layout.simple_spinner_item, MainActivity.accountArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountList.setAdapter(adapter);
    }


public void createCard(View v)  {
        try {
            Name = cardName.getText().toString();
            boolean switchContact = contactlessSwitch.isChecked();
            if (!switchContact)  {
                contactless = "Yes";
            }
            else if (switchContact)    {
                contactless = "No";
            }
            boolean switchType = typeSwitch.isChecked();
            if (!switchType)  {
                Type = "Debit";
            }
            else if (switchType)    {
                Type = "Credit";
            }
            accountList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 MainActivity.accountArrayList.get(position).createCard(Name, Type, contactless);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } finally {
        }
        }
}