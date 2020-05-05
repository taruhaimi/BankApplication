package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class LogIn extends AppCompatActivity {

    EditText GetUsername, GetPassword, CheckNumbers;
    TextView randNumbers;
    private String username = "Admin", password = "Admin", numbers;
    Context context = null;
    Random rand = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loadData();
        createAdmin();
        GetUsername = (EditText) findViewById(R.id.getUsername);
        GetPassword = (EditText) findViewById(R.id.getPassWord);
        randNumbers = (TextView) findViewById(R.id.securityCheckView);
        CheckNumbers = (EditText) findViewById(R.id.getNumbers);
        context = LogIn.this;
        rand = new Random();
        numbers = ("") + (rand.nextInt(899999) + 100000);
        randNumbers.setText(numbers);

    }

    public void goMainMenu(View v) {
        username = GetUsername.getText().toString();
        password = GetPassword.getText().toString();
        int flag = 1;
        int index = 1;
        for (int i = 0; i < MainActivity.userArrayList.size(); i++) {
            if (MainActivity.userArrayList.get(i).getName().equals(username) && MainActivity.userArrayList.get(i).getPassword().equals(password)) {
                flag = 0;
                index = i;
            }
        }
        if (flag == 0) {
            if ((CheckNumbers.getText().toString().equals(numbers)) == false) {
                Toast.makeText(context, "Invalid captcha code. Try again.", Toast.LENGTH_SHORT).show();
                numbers = "" + (rand.nextInt(899999) + 100000);
                randNumbers.setText(numbers);

            } else {
                Intent i = new Intent(LogIn.this, MainActivity.class);
                i.putExtra("key", index);
                startActivity(i);
                finish();
            }

        } else {
            Toast.makeText(context, "Username or password is not correct.\nTry again.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goSignUp(View v) {
        startActivity(new Intent(LogIn.this, SignUp.class));
    }

    public void createAdmin() {
        int adminExists = 0;
        for (int i = 0; i < MainActivity.userArrayList.size(); i++) {
            if (MainActivity.userArrayList.get(i).getName().equals("Admin")) {
                adminExists = 1;
                MainActivity.userArrayList.get(i).setName("Admin"); // TODO poista ennen palautusta
                MainActivity.userArrayList.get(i).setPassword("Admin");
            }
        }
            if (adminExists==0) {
                User user = new User();
                user.createUser("Admin", "Admin");
                System.out.println("Created admin user:\n Username: "+user.getName()+" Password "+user.getPassword());
                MainActivity.userArrayList.add(user);
            }
        }

    protected void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasklist", null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        MainActivity.userArrayList = gson.fromJson(json, type);

    }
}
