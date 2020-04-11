package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = SignUp.this;
    }

    public void Register(View v) {
        Toast.makeText(context,"Thank you for joining our community! :)\nYou are now registered. Log in and fill your contact information.", Toast.LENGTH_LONG).show();
        finish();
    }
}
