package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountsAndCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_and_cards);
    }

    public void goChooseAccount(View v) {
        startActivity(new Intent(AccountsAndCards.this,ChooseAccount.class));
    }

    public void goChooseCard(View v) {
        startActivity(new Intent(AccountsAndCards.this,ChooseCard.class));
    }

    public void goAddAccount(View v) {
        startActivity(new Intent(AccountsAndCards.this,AddAccount.class));
    }
    public void goAddCard(View v) {
        startActivity(new Intent(AccountsAndCards.this,AddCard.class));
    }

}
