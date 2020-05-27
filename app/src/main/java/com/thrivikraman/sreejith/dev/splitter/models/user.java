package com.thrivikraman.sreejith.dev.splitter.models;

import android.util.Patterns;

/* The model class for creating the object for the entity "user" */
public class user {

    private String email,password, fullName,phoneNumber;

    public user(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public user(String fullName,String email,String Password,String phone) {
        this.fullName = fullName;
        this.email = email;
        this.password = Password;
        this.phoneNumber = phone;
    }

    public String getEmail() { return email; }

    public String getFullName() { return fullName; }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public boolean validateEmailAddress() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }
}
