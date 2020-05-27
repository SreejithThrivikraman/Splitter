package com.thrivikraman.sreejith.dev.splitter.viewModels;

import android.view.View;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel
{
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Boolean> backPageStatus = new MutableLiveData<>();

    private MutableLiveData<user>   userMutableLiveData;

    public MutableLiveData<user> getUserInfo()
    {
        if(userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> isBackButtonPressed()
    {
        return backPageStatus;
    }
    public void onPressBackButton(View view) {
        backPageStatus.setValue(true);
    }

    public void onClick(View view) {
        user loginUser = new user(emailAddress.getValue(), password.getValue());
        userMutableLiveData.setValue(loginUser);
    }

    /* authenticateUser method is called when the user taps the login-in button.
       Creates a new user in 'Users' node on firebase */
    public boolean authenticateUser(user SampleUser)
    {
        return true;
    }

}
