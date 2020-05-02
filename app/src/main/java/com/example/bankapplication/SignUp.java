package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {
    Context context = null;
    EditText userName;
    EditText password;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = SignUp.this;
        userName = (EditText) findViewById(R.id.getUsername);
        password = (EditText) findViewById(R.id.getPassWord);
        register = (Button) findViewById(R.id.register);
    }

    public void Register(View v) {
        int flag = 0;
        String usName = userName.getText().toString();
        String psword = password.getText().toString();
        for (int i=0; i<MainActivity.userArrayList.size(); i++) {
            if (MainActivity.userArrayList.get(i).getName().equals(usName)) {
                Toast.makeText(this, "User name: " + usName + " is already taken.", Toast.LENGTH_SHORT).show();
                flag = 1;
            }
        }
        if (flag == 0) {
            Toast.makeText(context, "Thank you for joining our community! :)\nYou are now registered. Log in and fill your contact information.", Toast.LENGTH_LONG).show();
            User user = new User();
            user.createUser(usName, psword);
            MainActivity.userArrayList.add(user);
            finish();
        }
    }
}
