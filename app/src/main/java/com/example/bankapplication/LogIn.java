package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    EditText GetUsername, GetPassword;

    private String username = "Admin", password = "Admin";
    Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        GetUsername = (EditText) findViewById(R.id.getUsername);
        GetPassword = (EditText) findViewById(R.id.getPassWord);
        context = LogIn.this;

    }

    public void goMainMenu(View v) {
        /*if ((GetUsername.getText().toString().equals(username) && GetPassword.getText().toString().equals(password))*/
        if ((username.equals(username) && password.equals(password)) == false) {
            Toast.makeText(context,"Username or password is not correct. Try again.", Toast.LENGTH_SHORT).show();

        } else {
            startActivity(new Intent(LogIn.this, MainActivity.class));
            finish();
        }
    }

    public void goSignUp(View v) {
        startActivity(new Intent(LogIn.this, SignUp.class));
    }
}
