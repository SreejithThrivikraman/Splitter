package com.thrivikraman.sreejith.dev.splitter.models;

import android.util.Patterns;

import java.util.regex.Pattern;

public class user {

    private String email,password, fullName,phoneNumber;

    public user(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public user(String fullName,String email,String phone,String Password) {
        this.email = email;
        this.password = Password;
        this.fullName = fullName;
        this.phoneNumber = phone;
    }

// test class
    public user(String fullName,String email,String Password) {
        this.email = email;
        this.password = Password;
        this.fullName = fullName;
        this.phoneNumber = "111-111-1111";
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() { return fullName; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validateEmailAddress()
    {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }
}
