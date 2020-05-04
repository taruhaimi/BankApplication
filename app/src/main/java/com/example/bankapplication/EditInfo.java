package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditInfo extends AppCompatActivity {

    EditText Name, Address, Number, Email, Password1, Password2;

    private String name, address, number, email, password1, password2;
    Context context = null;

    public static final String SHARED_PREFS  ="sharedPrefs";
    public static final String NAME = "User";
    public static final String ADDRESS = "Address";
    public static final String NUMBER = "1234567890";
    public static final String EMAIL = "user.email@gmail.com";
    public static final String PASSWORD1 = "password";
    public static final String PASSWORD2 = "password";

    private String newName, newAddress, newNumber, newEmail, newPassword1, newPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Name = (EditText) findViewById(R.id.giveName);
        Address = (EditText) findViewById(R.id.giveAddress);
        Number = (EditText) findViewById(R.id.givePhoneNumber);
        Email = (EditText) findViewById(R.id.giveEmail);
        Password1 = (EditText) findViewById(R.id.givePassword1);
        Password2 = (EditText) findViewById(R.id.givePassword2);
        context = EditInfo.this;
        showInformation();
    }

    public void saveInformation(View v) {
        /*SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();*/
        newName = Name.getText().toString();
        newAddress = Address.getText().toString();
        newNumber = Number.getText().toString();
        newEmail = Email.getText().toString();
        newPassword1 = Password1.getText().toString();
        newPassword2 = Password2.getText().toString();
        MainActivity.userArrayList.get(MainActivity.currentIndex).setName(newName);
        MainActivity.userArrayList.get(MainActivity.currentIndex).setAddress(newAddress);
        MainActivity.userArrayList.get(MainActivity.currentIndex).setNumber(newNumber);
        MainActivity.userArrayList.get(MainActivity.currentIndex).setEmail(newEmail);


        /*editor.putString(NAME, Name.getText().toString());
        editor.putString(ADDRESS, Address.getText().toString());
        editor.putString(NUMBER, Number.getText().toString());
        editor.putString(EMAIL, Email.getText().toString());*/


        if (Password1.getText().toString().equals(Password2.getText().toString()) == false) {
            Toast.makeText(context,"Passwords do not match, try again.", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.userArrayList.get(MainActivity.currentIndex).setPassword(newPassword1);
           /* editor.putString(PASSWORD1, Password1.getText().toString());
            editor.putString(PASSWORD2, Password2.getText().toString());*/

            Toast.makeText(context,"Your personal information is now saved. You can go back safely.", Toast.LENGTH_SHORT).show();
        }
      //  editor.apply();
    }

    /*public void loadInformation() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        newName = sharedPreferences.getString(NAME, "");
        newAddress = sharedPreferences.getString(ADDRESS, "");
        newNumber = sharedPreferences.getString(NUMBER, "");
        newEmail = sharedPreferences.getString(EMAIL, "");
        newPassword1 = sharedPreferences.getString(PASSWORD1, "");
        newPassword2 = sharedPreferences.getString(PASSWORD2, "");

    }*/

    public void showInformation() {
        Name.setText(MainActivity.userArrayList.get(MainActivity.currentIndex).getName());
        Address.setText(MainActivity.userArrayList.get(MainActivity.currentIndex).getAddress());
        Number.setText(MainActivity.userArrayList.get(MainActivity.currentIndex).getNumber());
        Email.setText(MainActivity.userArrayList.get(MainActivity.currentIndex).getEmail());
    }
}
