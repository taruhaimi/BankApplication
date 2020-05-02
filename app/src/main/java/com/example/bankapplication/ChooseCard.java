package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ChooseCard extends AppCompatActivity {
    int listIndex;
    ListView cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_card);
        Bundle extras = getIntent().getExtras();
        cards = (ListView) findViewById(R.id.cardList);
        if (extras != null) {
            listIndex = extras.getInt("key");
        }

        ArrayAdapter<Card> arrayAdapter = new ArrayAdapter<Card>(this,android.R.layout.simple_list_item_1,MainActivity.accountArrayList.get(listIndex).cardArrayList);
        cards.setAdapter(arrayAdapter);
        cards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ChooseCard.this,CardInfo.class);
                i.putExtra("key", listIndex);
                i.putExtra("key2", position);
                startActivity(i);
            }
        });
    }
}
