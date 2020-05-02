package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{12,}" +               //at least 12 characters
                    "$");

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

    public void Register(View v) {
        if (validateUsername() && validatePassword()) {
            int flag = 0;
            String usName = userName.getEditText().getText().toString().trim();
            String psword = passWord.getEditText().getText().toString().trim();
            for (int i = 0; i < MainActivity.userArrayList.size(); i++) {
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
    private boolean validateUsername() {
        String usName = userName.getEditText().getText().toString().trim();

        if (usName.isEmpty()) {
            userName.setError("Field can't be empty.");
            return false;
        } else {
            passWord.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
            String psword = passWord.getEditText().getText().toString().trim();

            if (psword.isEmpty()) {
                passWord.setError("Field can't be empty.");
                return false;
            } else if (!PASSWORD_PATTERN.matcher(psword).matches()) {
                passWord.setError("Password too weak");
                return false;
            } else {
                passWord.setError(null);
                return true;
            }
    }
}
