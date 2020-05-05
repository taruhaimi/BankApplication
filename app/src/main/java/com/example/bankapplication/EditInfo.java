package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class EditInfo extends AppCompatActivity {

    EditText Name, Address, Number, Email;
    TextInputLayout passWord1, passWord2;
    Context context = null;
    private String newName, newAddress, newNumber, newEmail, newPassword1, newPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Name = (EditText) findViewById(R.id.giveName);
        Address = (EditText) findViewById(R.id.giveAddress);
        Number = (EditText) findViewById(R.id.givePhoneNumber);
        Email = (EditText) findViewById(R.id.giveEmail);
        passWord1 = (TextInputLayout) findViewById(R.id.getPassword1);
        passWord2 = (TextInputLayout) findViewById(R.id.getPassword2);
        context = EditInfo.this;
        showInformation();
    }

    public void saveInformation(View v) {
        newName = Name.getText().toString();
        newAddress = Address.getText().toString();
        newNumber = Number.getText().toString();
        newEmail = Email.getText().toString();
        newPassword1 = passWord1.getEditText().getText().toString();
        newPassword2 = passWord2.getEditText().getText().toString();
        MainActivity.userArrayList.get(MainActivity.currentIndex).setName(newName);
        MainActivity.userArrayList.get(MainActivity.currentIndex).setAddress(newAddress);
        MainActivity.userArrayList.get(MainActivity.currentIndex).setNumber(newNumber);
        MainActivity.userArrayList.get(MainActivity.currentIndex).setEmail(newEmail);



        if (validatePassword() && (!passWord1.getEditText().getText().toString().equals(passWord2.getEditText().getText().toString()))) {
                passWord2.setError("Passwords do not match, try again.");
        } else {
            MainActivity.userArrayList.get(MainActivity.currentIndex).setPassword(newPassword1);
            Toast.makeText(context,"Your personal information is now saved. You can go back safely.", Toast.LENGTH_SHORT).show();
        }
    }


    public void showInformation() {
        Name.setText(MainActivity.userArrayList.get(MainActivity.currentIndex).getName());
        Address.setText(MainActivity.userArrayList.get(MainActivity.currentIndex).getAddress());
        Number.setText(MainActivity.userArrayList.get(MainActivity.currentIndex).getNumber());
        Email.setText(MainActivity.userArrayList.get(MainActivity.currentIndex).getEmail());
    }

    private boolean validatePassword() {
        String psword = passWord1.getEditText().getText().toString().trim();

        if (psword.isEmpty()) {
            passWord1.setError("Field can't be empty.");
            return false;
        } else if (!SignUp.PASSWORD_PATTERN.matcher(psword).matches()) {
            passWord1.setError("Password is too weak. Your password must contain at least one number, special character, lower- and uppercase letter and is at least 12 characters long");
            return false;
        } else {
            passWord1.setError(null);
            return true;
        }
    }
}
