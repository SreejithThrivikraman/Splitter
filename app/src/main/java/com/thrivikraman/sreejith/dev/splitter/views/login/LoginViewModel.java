package com.thrivikraman.sreejith.dev.splitter.views.login;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.thrivikraman.sreejith.dev.splitter.Global.GlobalApplication;
import com.thrivikraman.sreejith.dev.splitter.models.Status;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import com.thrivikraman.sreejith.dev.splitter.networks.firbaseConnectivity;

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
    private Context appContext = GlobalApplication.getAppContext();

    public static final String SharedPref = "SHARED_PREF";
    public static final String shared_Pref_Email = "SHARED_PREF-EMAIL";
    public static final String shared_Pref_userName = "SHARED_PREF-USERNAME";
    private static String userName;

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
    public void authenticateUser(final user SampleUser)
    {
        authentication = FirebaseAuth.getInstance();
        authentication.signInWithEmailAndPassword(SampleUser.getEmail(), SampleUser.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            final FirebaseUser user = authentication.getCurrentUser();

                            Status.setValue(new Status("Login Success",true));

                            // Read the user name from the firebase database.
                            firbaseConnectivity conn = new firbaseConnectivity();
                            DatabaseReference ref = conn.getDatabasePath("Users");

                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for( DataSnapshot dp : dataSnapshot.getChildren())
                                    {
                                        if((dp.child("Details/email").getValue().toString()).equals(SampleUser.getEmail())) {
                                            userName = dp.child("Details/fullName").getValue().toString();
                                            // apply local storage of name and email

                                            break;
                                        } else {
                                            userName = "Unknown User";
                                        }
                                    }
                                    saveUserdata(SampleUser.getEmail(),userName);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.d(TAG, databaseError.toString());
                                }
                            });



                        } else {
                            Log.d(TAG, "signInWithEmail:failure");
                            Status.setValue(new Status(task.getException().getMessage() ,false));
                        }
                    }
                });
    }

    // method to store logged in user Email and User name in shared preference variable.
    public void saveUserdata(String email, String userName) {
        SharedPreferences sh = appContext.getSharedPreferences(SharedPref,Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();
        ed.putString(shared_Pref_Email,email);
        ed.putString(shared_Pref_userName,userName);
        ed.commit();
    }

}
