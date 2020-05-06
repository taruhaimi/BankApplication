package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AccountsAndCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_and_cards);
    }

    public void goChooseAccount(View v) {
        startActivity(new Intent(AccountsAndCards.this,ChooseAccount.class));
    }

    public void goAddMoney(View v) {
        if (MainActivity.userArrayList.get(MainActivity.currentIndex).getName().equals("Admin")) {
            startActivity(new Intent(AccountsAndCards.this, adminAddMoney.class));
        } else {
            Toast.makeText(this, "You do not have permission for this.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goAddAccount(View v) {
        startActivity(new Intent(AccountsAndCards.this,AddAccount.class));
    }

    public void goAddCard(View v) {
        startActivity(new Intent(AccountsAndCards.this,AddCard.class));
    }

    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }
}
