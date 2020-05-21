package com.thrivikraman.sreejith.dev.splitter.viewModels;

import android.view.View;

import com.thrivikraman.sreejith.dev.splitter.models.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {
    public MutableLiveData<String> FullName = new MutableLiveData<>();
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Boolean> backPageStatus = new MutableLiveData<>();
    private MutableLiveData<user>  userMutableLiveData;

    public MutableLiveData<user> getUserdata()
    {
        if(userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }


    public void onClickSignUp(View view) {
        user signUpUser = new user(emailAddress.getValue(), password.getValue(),FullName.getValue());
        userMutableLiveData.setValue(signUpUser);
    }

    public MutableLiveData<Boolean> isBackButtonPressed()
    {
        return backPageStatus;
    }
    public void onPressBackButton(View view) {
        backPageStatus.setValue(true);
    }
}
