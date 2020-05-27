package com.thrivikraman.sreejith.dev.splitter.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thrivikraman.sreejith.dev.splitter.models.Status;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class LoginViewModel extends ViewModel
{
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Boolean> backPageStatus = new MutableLiveData<>();
    private MutableLiveData<Status> Status = new MutableLiveData<>();
    private String TAG = "Debug : Login Status : ";
    private FirebaseAuth authentication;

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

    public MutableLiveData<Status> updateLoginInStatus()
    {
        return Status;
    }

    public void onClick(View view) {
        user loginUser = new user(emailAddress.getValue(), password.getValue());
        userMutableLiveData.setValue(loginUser);
    }

    /* authenticateUser method is called when the user taps the login-in button.
       Creates a new user in 'Users' node on firebase */
    public void authenticateUser(user SampleUser)
    {
        authentication = FirebaseAuth.getInstance();
        authentication.signInWithEmailAndPassword(SampleUser.getEmail(), SampleUser.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = authentication.getCurrentUser();
                            Status.setValue(new Status("Login Success",true));
                        } else {
                            Log.d(TAG, "signInWithEmail:failure");
                            Status.setValue(new Status(task.getException().getMessage() ,false));
                        }
                    }
                });
    }

}
