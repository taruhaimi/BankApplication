package com.example.bankapplication;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class SaveData extends AppCompatActivity {
    public static void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String json = gson.toJson(MainActivity.userArrayList);
        sharedPreferencesEditor.putString("tasklist", json);
        sharedPreferencesEditor.apply();
    }
}
