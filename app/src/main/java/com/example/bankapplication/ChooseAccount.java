package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

public class ChooseAccount extends AppCompatActivity {
    Context context = null;
    ListView accountview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = ChooseAccount.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
        accountview = (ListView) findViewById(R.id.accountList);

        final ArrayAdapter<Account> arrayAdapter = new ArrayAdapter<Account>(context,android.R.layout.simple_list_item_1,MainActivity.accountArrayList);
        accountview.setAdapter(arrayAdapter);
        accountview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ChooseAccount.this,AccountInfo.class);
                i.putExtra("key", position);
                startActivity(i);
            }
        });
    }

    protected void onResume()   {
        super.onResume();
        accountview.invalidateViews();
        accountview.refreshDrawableState();
    }
}
