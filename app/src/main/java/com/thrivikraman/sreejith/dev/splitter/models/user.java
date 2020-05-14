package com.thrivikraman.sreejith.dev.splitter.models;

import android.util.Patterns;

import java.util.regex.Pattern;

public class user {

    private String email,password;

    public user(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validateEmailAddress()
    {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }
}
