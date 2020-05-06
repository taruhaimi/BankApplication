package com.example.bankapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class AccountInfo extends AppCompatActivity {
    TextView accountNumber, accountMoney, accountType, accountInterest;
    EditText accountNameEdit;
    Switch Type;
    int listIndex;
    Date currentdate;
    double interesttime;
    Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        accountNumber = (TextView) findViewById(R.id.accountNumber);
        accountType = (TextView) findViewById(R.id.accountType);
        accountInterest =(TextView) findViewById(R.id.accountInterest);
        accountMoney = (TextView) findViewById(R.id.accountMoney);
        accountNameEdit = (EditText) findViewById(R.id.editName);
        Type = (Switch) findViewById(R.id.typeSwitch);
        Bundle extras = getIntent().getExtras();
        context = AccountInfo.this;

        // This adds Accounts information to text fields.
        if (extras != null) {
            listIndex = extras.getInt("key");
            accountNameEdit.setText(MainActivity.accountArrayList().get(listIndex).getInformation());
            accountNumber.setText("Account number: "+MainActivity.accountArrayList().get(listIndex).getID());
            accountType.setText("Account type: "+MainActivity.accountArrayList().get(listIndex).getType());
            accountInterest.setText("Interest: "+MainActivity.accountArrayList().get(listIndex).getRealInterest()+ "%");
            if (MainActivity.accountArrayList().get(listIndex).getType().equals("Savings")) {
                Type.setChecked(true);
                // Next lines gets the last time savings account was opened (or the first time) and calculates the interest it has gathered between that time and current time.
                // Interest updates every time you open this activity. Time in seconds is divided by minutes so the interest wont be too large overtime but its still noticeable while testing.
                currentdate = java.util.Calendar.getInstance().getTime();
                interesttime = (double) ((currentdate.getTime()-MainActivity.accountArrayList().get(listIndex).getInterestdate().getTime())/1000);
                MainActivity.accountArrayList().get(listIndex).setInterestdate(java.util.Calendar.getInstance().getTime());
                double exponent =  Math.pow(MainActivity.accountArrayList().get(listIndex).getInterest(),interesttime/60); // exponent is the interest to the power of interest time.
               MainActivity.accountArrayList().get(listIndex).depositMoney(MainActivity.accountArrayList().get(listIndex).getMoney()*exponent-MainActivity.accountArrayList().get(listIndex).getMoney());
            }
            accountMoney.setText("Account money: "+String.format("%1.2f",MainActivity.accountArrayList().get(listIndex).getMoney()) + "€");
        }
    }
    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }

    public void updateAccount(View v)   {
        MainActivity.accountArrayList().get(listIndex).setName(accountNameEdit.getText().toString());
        boolean state = Type.isChecked();
        if (!state) {
            MainActivity.accountArrayList().get(listIndex).setInterest(1);
            MainActivity.accountArrayList().get(listIndex).setType("Current");
            accountInterest.setText("Interest: "+MainActivity.accountArrayList().get(listIndex).getRealInterest()+ "%");
            accountType.setText("Account type: "+MainActivity.accountArrayList().get(listIndex).getType());
        }
        else {
            MainActivity.accountArrayList().get(listIndex).setInterest(1.02);
            MainActivity.accountArrayList().get(listIndex).setType("Savings");
            accountType.setText("Account type: "+MainActivity.accountArrayList().get(listIndex).getType());
            accountInterest.setText("Interest: "+MainActivity.accountArrayList().get(listIndex).getRealInterest()+ "%");
        }
        Toast.makeText(context,"Updates saved.", Toast.LENGTH_SHORT).show();


    }
    public void deleteAccount(View v)   {
        // this method deletes account via confirmation window
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete Account");
        builder.setMessage("Are you sure you want to delete this account?\nPress cancel to cancel.");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.accountArrayList().remove(listIndex);
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

    public void accountTransactions(View w) {
        Intent i = new Intent(AccountInfo.this,TransActions.class);
        i.putExtra("key", listIndex);
        startActivity(i);
    }

    protected void onResume()    {
        super.onResume();
        //Updates the view
        accountNameEdit.setText(MainActivity.accountArrayList().get(listIndex).getInformation());
        accountNumber.setText("Account number: "+MainActivity.accountArrayList().get(listIndex).getID());
        accountType.setText("Account type: "+MainActivity.accountArrayList().get(listIndex).getType());
        accountMoney.setText("Money: "+String.format("%1.2f", MainActivity.accountArrayList().get(listIndex).getMoney()) + "€");
    }
}