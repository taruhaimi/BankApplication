package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditCardSettings extends AppCompatActivity {

    EditText withdrawLimit;
    EditText paymentLimit;
    Spinner regionsList;
    EditText creditLimit;
    Context context = null;

    public static final String SHARED_PREFS  ="sharedPrefs";
    public static final String WITHDRAWLIMIT = "500";
    public static final String PAYMENTLIMIT = "500";
    public static final String CREDITLIMIT = "2000";
    public static final String REGION = "";

    private String newWithdrawLimit, newPaymentLimit, newCreditLimit, newRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card_settings);
        withdrawLimit = (EditText) findViewById(R.id.withdrawLimit);
        paymentLimit = (EditText) findViewById(R.id.paymentLimit);
        creditLimit = (EditText) findViewById(R.id.creditLimit);
        regionsList = (Spinner) findViewById(R.id.regionsList);
        context = EditCardSettings.this;
        //regionsList.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Finland");
        categories.add("Nordic countries");
        categories.add("Europe");
        categories.add("World");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionsList.setAdapter(dataAdapter);

        loadSettings();
        showSettings();
    }

    public void saveSettings(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences.edit();

        editor2.putString(WITHDRAWLIMIT, withdrawLimit.getText().toString());
        editor2.putString(PAYMENTLIMIT, paymentLimit.getText().toString());
        editor2.putString(CREDITLIMIT, creditLimit.getText().toString());
        editor2.putString(REGION, regionsList.getAdapter().toString());

        editor2.apply();

        Toast.makeText(context,"Your personal information is now saved. You can go back safely.", Toast.LENGTH_SHORT).show();
    }

    public void loadSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        newWithdrawLimit = sharedPreferences.getString(WITHDRAWLIMIT, "");
        newPaymentLimit = sharedPreferences.getString(PAYMENTLIMIT, "");
        newCreditLimit = sharedPreferences.getString(CREDITLIMIT, "");
        newRegion = sharedPreferences.getString(REGION, "");

    }

    public void showSettings() {
        withdrawLimit.setText(newWithdrawLimit);
        paymentLimit.setText(newPaymentLimit);
        creditLimit.setText(newCreditLimit);
        //regionsList.setAdapter().toString(newRegion); //TODO: Spinnerin arvon tallennus?
    }

}