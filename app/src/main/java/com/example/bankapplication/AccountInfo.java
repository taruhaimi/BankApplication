package com.example.bankapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AccountInfo extends AppCompatActivity {
    TextView accountName;
    TextView accountNumber;
    TextView accountMoney;
    TextView accountType;
    TextView accountInterest;
    int listIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        accountName = (TextView) findViewById(R.id.accountName);
        accountNumber = (TextView) findViewById(R.id.accountNumber);
        accountType = (TextView) findViewById(R.id.accountType);
        accountInterest =(TextView) findViewById(R.id.accountInterest);
        accountMoney = (TextView) findViewById(R.id.accountMoney);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listIndex = extras.getInt("key");
            System.out.println(listIndex);
            accountName.setText("Name: "+MainActivity.accountArrayList.get(listIndex).getInformation());
            accountNumber.setText("Account number: "+MainActivity.accountArrayList.get(listIndex).getID());
            accountType.setText("Account type: "+MainActivity.accountArrayList.get(listIndex).getType());
            accountMoney.setText("Account money: "+MainActivity.accountArrayList.get(listIndex).getMoney());
            if (MainActivity.accountArrayList.get(listIndex).getType().equals("Saving")) {
            accountInterest.setText("Interest: "+Double.toString(MainActivity.accountArrayList.get(listIndex).getInterest()));
        }
        }
    }

    public void deleteAccount(View v)   {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete Account");
        builder.setMessage("Are you sure you want to delete this account?\nPress cancel to cancel.");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.accountArrayList.remove(listIndex);
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addMoney(View v)    {
        Intent i = new Intent(AccountInfo.this,AddMoney.class);
        i.putExtra("key", listIndex);
        startActivity(i);
    }

    public void newPayment(View v)  {
        Intent i = new Intent(AccountInfo.this,NewPayment.class);
        i.putExtra("key", listIndex);
        startActivity(i);
    }
    public void showCards(View v)   {
        Intent i = new Intent(AccountInfo.this,ChooseCard.class);
        i.putExtra("key", listIndex);
        startActivity(i);
    }
    protected void onResume()    {
        super.onResume();
        accountName.setText("Name: "+MainActivity.accountArrayList.get(listIndex).getInformation());
        accountNumber.setText("Account number: "+MainActivity.accountArrayList.get(listIndex).getID());
        accountType.setText("Account type: "+MainActivity.accountArrayList.get(listIndex).getType());
        accountMoney.setText("Account type: "+MainActivity.accountArrayList.get(listIndex).getMoney());
    }
}