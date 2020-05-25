package com.thrivikraman.sreejith.dev.splitter.viewModels;

import android.view.View;


import com.google.firebase.database.DatabaseReference;
import com.thrivikraman.sreejith.dev.splitter.models.expenses;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import com.thrivikraman.sreejith.dev.splitter.networks.firbaseConnectivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {

    public MutableLiveData<String> FullName = new MutableLiveData<>();
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Boolean> backPageStatus = new MutableLiveData<>();
    private MutableLiveData<user>  userMutableLiveData;
    private DatabaseReference ref;
    private firbaseConnectivity connection;
    private String mGroupId;

    public SignInViewModel() {
        connection = new firbaseConnectivity();
    }

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


    /* createUser method is called when the user taps the sign-in button.
       Creates a new user in 'Users' node on firebase */
    public boolean createUser(user SampleUser, expenses default_user)
    {   
         ref = connection.getDatabasePath("Users");
         mGroupId = ref.push().getKey();
         ref.child(mGroupId).child("Details").setValue(SampleUser);
         ref.child(mGroupId).child("Friends").setValue("null");
         ref.child(mGroupId).child("expenses").push().setValue(default_user);
         return true;
    }
}
