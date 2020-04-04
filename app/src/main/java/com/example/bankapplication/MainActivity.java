package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goEditInfo(View v) {
        startActivity(new Intent(MainActivity.this,EditInfo.class));
    }

    public void goAccountsAndCards(View v) {
        startActivity(new Intent(MainActivity.this, AccountsAndCards.class));
    }

    public void goLogIn(View v) {
        startActivity(new Intent(MainActivity.this, LogIn.class));
    }


}
