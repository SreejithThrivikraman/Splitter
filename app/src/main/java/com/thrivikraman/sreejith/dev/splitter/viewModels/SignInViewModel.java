package com.thrivikraman.sreejith.dev.splitter.viewModels;

import android.util.Log;
import android.view.View;
import com.thrivikraman.sreejith.dev.splitter.models.loginStatus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.thrivikraman.sreejith.dev.splitter.models.expenses;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import com.thrivikraman.sreejith.dev.splitter.networks.firbaseConnectivity;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {

    public MutableLiveData<String> FullName = new MutableLiveData<>();
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> phone = new MutableLiveData<>();
    public MutableLiveData<Boolean> backPageStatus = new MutableLiveData<>();
    private MutableLiveData<loginStatus> Status = new MutableLiveData<>();
    private MutableLiveData<user>  userMutableLiveData;
    private DatabaseReference ref;
    private firbaseConnectivity connection;
    private FirebaseAuth authentication;
    private String mGroupId;
    private String TAG = "Firebase auth - User Creation status";

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
        user signUpUser = new user(FullName.getValue(),emailAddress.getValue(), password.getValue(), phone.getValue());
        userMutableLiveData.setValue(signUpUser);
    }

    public MutableLiveData<loginStatus> updateSignInStatus()
    {
        return Status;
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
    
    public void createUser(final user SampleUser, final expenses default_user)
    {    authentication = FirebaseAuth.getInstance();

        authentication.createUserWithEmailAndPassword(SampleUser.getEmail(), SampleUser.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Status.setValue(new loginStatus("Success",true));
                            ref = connection.getDatabasePath("Users");
                            mGroupId = ref.push().getKey();
                            ref.child(mGroupId).child("Details").setValue(SampleUser);
                            ref.child(mGroupId).child("Friends").setValue("null");
                            ref.child(mGroupId).child("expenses").push().setValue(default_user);

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Status.setValue(new loginStatus(task.getException().getMessage(),false));

                        }
                    }
                });
    }

}
