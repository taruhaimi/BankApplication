package com.example.bankapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class TransActions extends AppCompatActivity {
    int listIndex;
    Context context = null;
    TextView text;
    ListView transactionsList;
    int index;
    double amount = 0.0;
    Button write;
    /*public static void writeCsv(int i, int type, String subject, double moneyAmount) {
        int style = type;
        String who = subject;
        double amount = moneyAmount;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_actions);
        context = TransActions.this;
        transactionsList = (ListView) findViewById(R.id.transactionsList);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listIndex = extras.getInt("key");
        }
        //ArrayAdapter<TransActionInfo> arrayAdapter = new ArrayAdapter<Account>(this, android.R.layout.simple_list_item_1, MainActivity.accountArrayList().get(listIndex).TransActionInfo);
       // transactionsList.setAdapter(arrayAdapter);
        transactionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });


    }

}
