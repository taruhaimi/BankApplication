package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    Context context = null;
    Button register;
    TextInputLayout userName, passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = SignUp.this;
        userName = (TextInputLayout) findViewById(R.id.username);
        register = (Button) findViewById(R.id.registerBtn);
        passWord = (TextInputLayout) findViewById(R.id.password);
    }

    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }

    public void Register(View v) {
        if (Validater.validateUsername(userName) && Validater.validateNewPassword(passWord)) {
            int flag = 0;
            String usName = userName.getEditText().getText().toString().trim();
            String psword = passWord.getEditText().getText().toString().trim();

            if (usName.equals("Admin")) {
                Toast.makeText(this, "You can not name your user Admin.", Toast.LENGTH_SHORT).show();
                flag = 1;
            }
            
            for (int i = 0; i < MainActivity.userArrayList.size(); i++) {
                if (MainActivity.userArrayList.get(i).getName().equals(usName)) {
                    Toast.makeText(this, "Username: " + usName + " is already taken.", Toast.LENGTH_SHORT).show();
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
}
