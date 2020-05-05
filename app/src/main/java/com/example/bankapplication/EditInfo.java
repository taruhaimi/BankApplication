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
    @Override
    protected  void onPause() {
        // Saves data to sharedpreferences using Gson-library
        super.onPause();
        SaveData.save(this);
    }
    public void saveInformation(View v) {
        newName = Name.getText().toString();
        newAddress = Address.getText().toString();
        newNumber = Number.getText().toString();
        newEmail = Email.getText().toString();
        newPassword1 = passWord1.getEditText().getText().toString();
        newPassword2 = passWord2.getEditText().getText().toString();

        if (MainActivity.userArrayList.get(MainActivity.currentIndex).getName().equals("Admin")) {

            if (!Name.getText().toString().equals("Admin")) {
                Toast.makeText(context, "You can not change Admin user's username.", Toast.LENGTH_SHORT).show();
            } else {

                if (!passWord1.getEditText().getText().toString().trim().isEmpty() ) {
                    passWord1.setError("You can not change Admin user's password.");
                }

                MainActivity.userArrayList.get(MainActivity.currentIndex).setName("Admin");
                MainActivity.userArrayList.get(MainActivity.currentIndex).setAddress(newAddress);
                MainActivity.userArrayList.get(MainActivity.currentIndex).setNumber(newNumber);
                MainActivity.userArrayList.get(MainActivity.currentIndex).setEmail(newEmail);
                MainActivity.userArrayList.get(MainActivity.currentIndex).setPassword("Admin");
                Toast.makeText(context, "Your personal information is now saved. You can go back safely.", Toast.LENGTH_SHORT).show();
            }

        } else {
            MainActivity.userArrayList.get(MainActivity.currentIndex).setAddress(newAddress);
            MainActivity.userArrayList.get(MainActivity.currentIndex).setNumber(newNumber);
            MainActivity.userArrayList.get(MainActivity.currentIndex).setEmail(newEmail);

            if (Name.getText().toString().equals("Admin")) {
                Toast.makeText(context, "You can not name your user Admin,\ntry again.", Toast.LENGTH_SHORT).show();
            } else {
                int flag = 0;
                for (int i = 0; i < MainActivity.userArrayList.size(); i++) {
                    if (MainActivity.userArrayList.get(i).getName().equals(newName)) {
                        Toast.makeText(this, "Username: " + newName + " is already taken.", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    MainActivity.userArrayList.get(MainActivity.currentIndex).setName(newName);
                    Toast.makeText(context, "Your personal information is now saved. You can go back safely.", Toast.LENGTH_SHORT).show();
                }
            }

            if (validatePassword()) {
                if (!passWord1.getEditText().getText().toString().equals(passWord2.getEditText().getText().toString())) {
                    passWord2.setError("Passwords do not match, try again.");
                } else {
                    MainActivity.userArrayList.get(MainActivity.currentIndex).setPassword(newPassword1);
                    Toast.makeText(context, "Your personal information is now saved. You can go back safely.", Toast.LENGTH_SHORT).show();
                }
            }

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
            passWord1.setError("If you want to change your password,\nfield can not be empty.");
            return false;
        } else if (!SignUp.PASSWORD_PATTERN.matcher(psword).matches()) {
            passWord1.setError("Password is too weak. Your password must contain at least one number, special character, lower- and uppercase letter and is at least 12 characters long.");
            return false;
        } else {
            passWord1.setError(null);
            return true;
        }
    }
}
