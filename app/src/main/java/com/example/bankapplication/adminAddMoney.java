package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class adminAddMoney extends AppCompatActivity {

    ArrayList<Account> allAccounts = new ArrayList<Account>();
    EditText moneyText;
    ListView accounts;
    int index;
    double moneyAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_money);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        accounts = (ListView) findViewById(R.id.allAccounts);
        moneyText = (EditText) findViewById(R.id.getMoney2);

        for (int i = 0; i < MainActivity.userArrayList.size(); i++) {
            allAccounts.addAll(MainActivity.userArrayList.get(i).accountArrayList);
        }

        ArrayAdapter<Account> arrayAdapter = new ArrayAdapter<Account>(this, android.R.layout.simple_list_item_1, allAccounts);
        accounts.setAdapter(arrayAdapter);
        accounts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    public void transferMoney(View v)   {
        try {
            moneyAmount = Double.parseDouble(moneyText.getText().toString());
        } catch (NullPointerException e)    {
            Toast.makeText(this, "Field can't be empty.", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e){
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }

        // This loop finds the chosen account from the list that contains all users and add money there.
        for (int i = 0; i < MainActivity.userArrayList.size(); i++) {
            for (int k = 0; k < MainActivity.userArrayList.get(i).accountArrayList.size();k++)  {
                if (MainActivity.userArrayList.get(i).accountArrayList.get(k).equals(allAccounts.get(index)))   {
                    MainActivity.userArrayList.get(i).accountArrayList.get(k).depositMoney(moneyAmount);
                    MainActivity.userArrayList.get(i).accountArrayList.get(k).createTransaction("Bank", MainActivity.userArrayList.get(i).accountArrayList.get(k).getID(),moneyAmount, "Bank's Transfer");
                    Toast.makeText(this, "Added "+moneyAmount+"€ to account "+MainActivity.userArrayList.get(i).accountArrayList.get(k).getID(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        accounts.invalidateViews();
        accounts.refreshDrawableState();

    }
}
