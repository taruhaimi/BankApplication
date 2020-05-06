package com.example.bankapplication;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Validater {

    //This Pattern defines the terms that created password must comply. It's used all over the app.
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{12,}" +               //at least 12 characters
                    "$");

    static boolean validateUsername(TextInputLayout userName) {
        String usName = userName.getEditText().getText().toString().trim();

        if (usName.isEmpty()) {
            userName.setError("Field can't be empty.");
            return false;
        } else {
            userName.setError(null);
            return true;
        }
    }

    static boolean validateNewPassword(TextInputLayout passWord) {
        String psword = passWord.getEditText().getText().toString().trim();

        if (psword.isEmpty()) {
            passWord.setError("Field can't be empty.");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(psword).matches()) {
            passWord.setError("Password is too weak. Your password must contain at least one number, special character, lower- and uppercase letter and is at least 12 characters long");
            return false;
        } else {
            passWord.setError(null);
            return true;
        }
    }

    static boolean validateExistingPassword(TextInputLayout passWord) {
        String psword = passWord.getEditText().getText().toString().trim();

        if (psword.isEmpty()) {
            passWord.setError("If you want to change your password,\nfield can not be empty.");
            return false;
        } else if (!Validater.PASSWORD_PATTERN.matcher(psword).matches()) {
            passWord.setError("Password is too weak. Your password must contain at least one number, special character, lower- and uppercase letter and is at least 12 characters long.");
            return false;
        } else {
            passWord.setError(null);
            return true;
        }
    }
}
