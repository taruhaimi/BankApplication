package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddMoney extends AppCompatActivity {
    int listIndex;
    double money;
    EditText moneyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        Bundle extras = getIntent().getExtras();
        moneyText = (EditText) findViewById(R.id.getMoney);
        if (extras != null) {
            listIndex = extras.getInt("key");
        }
        //moneyText.setText("0");
    }

    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }
    public void addMoney(View v) {
        try {
            money = Double.parseDouble(moneyText.getText().toString());
            MainActivity.accountArrayList().get(listIndex).depositMoney(money);
            Toast.makeText(this, "Added " + money + "€ to account " + MainActivity.accountArrayList().get(listIndex).getInformation(), Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
        catch (NullPointerException e)  {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
