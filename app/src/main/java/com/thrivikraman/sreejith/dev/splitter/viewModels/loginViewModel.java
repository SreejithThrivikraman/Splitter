package com.thrivikraman.sreejith.dev.splitter.viewModels;

import com.thrivikraman.sreejith.dev.splitter.models.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class loginViewModel extends ViewModel
{
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<user>   userMutableLiveData;

    public MutableLiveData<user> getUserInfo()
    {
        if(userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }
}
