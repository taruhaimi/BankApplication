package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SelfPayment extends AppCompatActivity {
    int listIndex;
    int listposition;
    double moneyAmount;
    ListView accountview;
    EditText withdrawAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_payment);
        accountview = (ListView) findViewById(R.id.accountList);
        withdrawAmount = (EditText) findViewById(R.id.moneyAmount);
        //withdrawAmount.setText("0.00");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listIndex = extras.getInt("key");
        }
        final ArrayAdapter<Account> arrayAdapter = new ArrayAdapter<Account>(this,android.R.layout.simple_list_item_1,MainActivity.accountArrayList);
        accountview.setAdapter(arrayAdapter);
        accountview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listposition = position;
            }
        });
    }

    public void AccountPay(View v)  {
        try {
            moneyAmount = Double.parseDouble(withdrawAmount.getText().toString());
            if (MainActivity.accountArrayList.get(listIndex).getMoney() < moneyAmount) {
                Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.accountArrayList.get(listIndex).withdrawMoney(moneyAmount);
                MainActivity.accountArrayList.get(listposition).depositMoney(moneyAmount);
                Toast.makeText(this, "Transferred " + moneyAmount + "€ from account " + MainActivity.accountArrayList.get(listIndex).getInformation() + " to account " + MainActivity.accountArrayList.get(listposition).getInformation() + " successfully.", Toast.LENGTH_LONG).show();
                accountview.invalidateViews();
                accountview.refreshDrawableState();
                //TransActions.writeCsv( 2, "Me", moneyAmount);//TODO: Kutsu?
            }
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
        catch (NullPointerException e)  {
            Toast.makeText(this, "Invalid input, try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
