package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        GetUsername = (EditText) findViewById(R.id.getUsername);
        GetPassword = (EditText) findViewById(R.id.getPassWord);
        randNumbers = (TextView) findViewById(R.id.securityCheckView);
        CheckNumbers = (EditText) findViewById(R.id.getNumbers);
        context = LogIn.this;

        rand = new Random();
        numbers = ("") + (rand.nextInt(899999)+100000);
        randNumbers.setText(numbers);

    }

    public void goMainMenu(View v) {
        /*if ((GetUsername.getText().toString().equals(username) && GetPassword.getText().toString().equals(password)) {
        */ if ((CheckNumbers.getText().toString().equals(numbers)) == false) {
            Toast.makeText(context, "Invalid captcha code. Try again.", Toast.LENGTH_SHORT).show();
            numbers = "" + (rand.nextInt(899999)+100000);
            randNumbers.setText(numbers);

            }

        if ((username.equals(username) && password.equals(password)) == false) {
            Toast.makeText(context, "Username or password is not correct. Try again.", Toast.LENGTH_SHORT).show();

        } else {
            startActivity(new Intent(LogIn.this, MainActivity.class));
            finish();
        }
    }

    public void goSignUp(View v) {
        startActivity(new Intent(LogIn.this, SignUp.class));
    }
}
