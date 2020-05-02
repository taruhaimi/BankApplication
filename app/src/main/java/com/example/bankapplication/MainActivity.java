package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<User> userArrayList = new ArrayList<User>();
    private static int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentIndex = extras.getInt("key");
        }
    }
    public static ArrayList<Account> accountArrayList()    {
        // This is a static method so we can call a specific users' accounts in all classes.
        // It returns arraylist of accounts which belongs to the user you have logged into
        return userArrayList.get(currentIndex).accountArrayList;
    }

    @Override
    protected void onDestroy()   {
        super.onDestroy();
        saveData();
        System.out.println("Testi1");
        finish();
    }

    @Override
    protected void onPause()   {
        super.onPause();
        saveData();
    }

    @Override
    protected  void onStop()   {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.userArrayList);
        editor.putString("tasklist", json);
        editor.apply();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userArrayList);
        editor.putString("tasklist", json);
        editor.apply();
    }


    public void goEditInfo(View v) {
        startActivity(new Intent(MainActivity.this,EditInfo.class));
    }

    public void goAccountsAndCards(View v) {
        startActivity(new Intent(MainActivity.this, AccountsAndCards.class));
    }

    public void goLogIn(View v) {
        startActivity(new Intent(MainActivity.this, LogIn.class));
        finish();
    }

    public void goCardPaymentSimulation(View v) {
        startActivity(new Intent(MainActivity.this, CardPayment.class));
    }

    public void goCredits(View v) {
        startActivity(new Intent(MainActivity.this, Credits.class));
    }


}
