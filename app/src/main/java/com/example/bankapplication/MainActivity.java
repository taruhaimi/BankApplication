package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Account> accountArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    @Override
    protected void onDestroy()   {
        super.onDestroy();
        saveData();
        System.out.println("Testi1");
        finish();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(accountArrayList);
        editor.putString("tasklist", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasklist", null);
        Type type = new TypeToken<ArrayList<Account>>() {}.getType();
        accountArrayList = gson.fromJson(json, type);
        if(accountArrayList == null) {
            accountArrayList = new ArrayList<Account>();
        }
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


}
