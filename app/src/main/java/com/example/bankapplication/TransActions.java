package com.example.bankapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class TransActions extends AppCompatActivity {

    int listIndex;
    Context context = null;
    String[] transactions = { };
    TextView text;
    String filename = "transactions.csv";
    String writing = "";
    String who = "you";
    double amount = 0.0;

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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listIndex = extras.getInt("key");
        }

        text = (TextView) findViewById(R.id.textView);

    }

    /*public void readCsv(View v) {
        try {
            InputStream ins = context.openFileInput(filename);

            CSVReader csvrd = new CSVReader(new InputStreamReader(ins));
            String [] nextLine;
            while ((nextLine = csvrd.readNext()) != null) {
                text.setText(nextLine[0] + nextLine[1] + nextLine[2]);
                System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + "luetaan");
                Log.d("VariableTag", nextLine[0]);
            }
            csvrd.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "File was not found", Toast.LENGTH_SHORT).show();
        } finally {
            System.out.println("LUETTU");
        }
    }

    public void writeCsv(View v) {
        System.out.println("tiedosto:" + filename);
        try {
            context.openFileOutput(filename, Context.MODE_PRIVATE);

            CSVWriter writer = new CSVWriter(new FileWriter(filename));
            //feed in your array (or convert your data to an array)
            String[] writing = "first#second#third".split("#");
            writer.writeNext(writing);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "File was not found", Toast.LENGTH_SHORT).show();
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }*/


    public void readCsv(View v) {
        try {
            InputStream ins = context.openFileInput(filename);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s=br.readLine()) != null) {
                text.setText(s);
            }
            ins.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "File was not found", Toast.LENGTH_SHORT).show();
        } finally {
            System.out.println("LUETTU");
        }
    }

    public void writeCsv(int style, String who, double amount) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            if (style == 1) { //Payed money
                writing = "Recipient: " + who + "          Amount: -" + amount;
            }
            else if (style == 2) { //Received money
                writing = "Payer: " + who + "          Amount: " + amount;
            }
            osw.write(writing);
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "File was not found", Toast.LENGTH_SHORT).show();
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }

}
