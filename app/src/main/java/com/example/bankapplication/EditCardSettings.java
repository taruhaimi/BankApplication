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
import java.util.Arrays;
import java.util.List;

public class EditCardSettings extends AppCompatActivity {

    EditText withdrawLimit, paymentLimit, creditLimit;
    Spinner regionsList, regionsList2;
    Context context = null;
    int listIndex, position, region1, region2;

    private String newWithdrawLimit, newPaymentLimit, newCreditLimit, newRegion1, newRegion2;

    public static List<String> categories = new ArrayList<String>(Arrays.asList("Finland","Nordic countries", "Europe", "World")){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card_settings);
        withdrawLimit = (EditText) findViewById(R.id.withdrawLimit);
        paymentLimit = (EditText) findViewById(R.id.paymentLimit);
        creditLimit = (EditText) findViewById(R.id.creditLimit);
        regionsList = (Spinner) findViewById(R.id.regionsList);
        regionsList2 = (Spinner) findViewById(R.id.regionsList2);
        context = EditCardSettings.this;
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            listIndex = extras.getInt("key");
            position = extras.getInt("key2");
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionsList.setAdapter(dataAdapter);
        regionsList2.setAdapter(dataAdapter);

        //showSettings();
    }

    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }

    public void saveSettings(View v) {

        newWithdrawLimit = withdrawLimit.getText().toString();
        newPaymentLimit = paymentLimit.getText().toString();
        newCreditLimit = creditLimit.getText().toString();
        newRegion1 = Integer.toString(regionsList.getSelectedItemPosition());
        newRegion2 = Integer.toString(regionsList2.getSelectedItemPosition());


        MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).setWithdrawLimit(newWithdrawLimit);
        MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).setPaymentLimit(newPaymentLimit);
        MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).setCreditLimit(newCreditLimit);
        MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).setRegionLimitWithDraw(newRegion1);
        MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).setRegionLimitPayment(newRegion2);

        Toast.makeText(context,"Your card settings are now saved. You can go back safely.", Toast.LENGTH_SHORT).show();
    }
/*
    public void showSettings() {

        withdrawLimit.setText(MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getWithdrawLimit());
        paymentLimit.setText(MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getPaymentLimit());
        creditLimit.setText(MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getCreditLimit());

        region1 = Integer.parseInt(MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getRegionLimitWithDraw());
        region2 = Integer.parseInt(MainActivity.accountArrayList().get(listIndex).cardArrayList.get(position).getRegionLimitPayment());
        regionsList.setSelection(region1);
        regionsList2.setSelection(region2);

    }

 */

}