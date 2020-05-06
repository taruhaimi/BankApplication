package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddCard extends AppCompatActivity {
    EditText cardName;
    TextInputLayout pinCode;
    ListView listAccount;
    Switch contactlessSwitch, typeSwitch;
    String Name, contactless, Type, code;
    int index = 0; //listAccounts clicked index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        cardName = (EditText) findViewById(R.id.giveCardName);
        pinCode = (TextInputLayout) findViewById(R.id.getPincode);
        contactlessSwitch = (Switch) findViewById(R.id.choosePayment);
        typeSwitch = (Switch) findViewById(R.id.chooseType);
        listAccount = (ListView) findViewById(R.id.accountList);

        ArrayAdapter<Account> arrayAdapter = new ArrayAdapter<Account>(this, android.R.layout.simple_list_item_1, MainActivity.accountArrayList());
        listAccount.setAdapter(arrayAdapter);
        listAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });
    }

    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }

    public void createCard(View v) {
        if (validatePinCode()) {
            Name = cardName.getText().toString();
            code = pinCode.getEditText().getText().toString();

            boolean switchContact = contactlessSwitch.isChecked();
            if (!switchContact) {
                contactless = "No";
            } else if (switchContact) {
                contactless = "Yes";
            }

            boolean switchType = typeSwitch.isChecked();
            if (!switchType) {
                Type = "Debit";
            } else if (switchType) {
                Type = "Credit";
            }

            MainActivity.accountArrayList().get(index).createCard(Name, Type, contactless, code);
            Toast.makeText(this, "Created card: " + Name, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validatePinCode() {
        String psword = pinCode.getEditText().getText().toString().trim();

        if (psword.isEmpty()) {
            pinCode.setError("Field can't be empty.");
            return false;
        } else if (psword.length() != 4) {
            pinCode.setError("Pin code must be exactly four digits long.");
            return false;
        } else {
            pinCode.setError(null);
            return true;
        }
    }
}