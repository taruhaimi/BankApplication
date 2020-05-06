package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;

public class EditInfo extends AppCompatActivity {

    EditText Name, Address, Number, Email;
    TextInputLayout passWord1, passWord2;
    Context context = null;
    String newName, newAddress, newNumber, newEmail, newPassword1, newPassword2;

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

    // This method takes care of that when user want's to change their personal information, that data will be changed and saved. It also checks,
    // that Admin-user can't change its username or password, and basic-user can't change it's name to Admin or something that already exists.
    // When changing password, it has to match criteria.
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
                    if (MainActivity.userArrayList.get(i).getName().equals(newName) && !MainActivity.userArrayList.get(MainActivity.currentIndex).getName().equals(newName)) {
                        Toast.makeText(this, "Username: " + newName + " is already taken.", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    MainActivity.userArrayList.get(MainActivity.currentIndex).setName(newName);
                    Toast.makeText(context, "Your personal information is now saved. You can go back safely.", Toast.LENGTH_SHORT).show();
                }
            }

            if (Validater.validateExistingPassword(passWord1)) {
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

}
