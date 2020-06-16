package com.thrivikraman.sreejith.dev.splitter.views.signin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.thrivikraman.sreejith.dev.splitter.Global.GlobalApplication;
import com.thrivikraman.sreejith.dev.splitter.models.Status;
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
    private MutableLiveData<Status> Status = new MutableLiveData<>();
    private MutableLiveData<user>  userMutableLiveData;
    private DatabaseReference ref;
    private firbaseConnectivity connection;
    private FirebaseAuth authentication;
    private String mGroupId;
    private String TAG = "Firebase auth - User Creation status";
    private Context appContext = GlobalApplication.getAppContext();

    public static final String SharedPref = "SHARED_PREFC";
    public static final String shared_Pref_Email = "SHARED_PREF-EMAIL";
    public static final String shared_Pref_userName = "SHARED_PREF-USERNAME";
    private static String userName;

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

    public MutableLiveData<Status> updateSignInStatus()
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

                            ref = connection.getDatabasePath("Users");
                            mGroupId = ref.push().getKey();
                            ref.child(mGroupId).child("Details").setValue(SampleUser);
                            ref.child(mGroupId).child("Friends").setValue("null");
                            ref.child(mGroupId).child("expenses").push().setValue(default_user);


                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for( DataSnapshot dp : dataSnapshot.getChildren())
                                    {
                                        if((dp.child("Details/email").getValue().toString()).equals(SampleUser.getEmail())) {
                                            userName = dp.child("Details/fullName").getValue().toString();
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


                            Status.setValue(new Status("Success",true));
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Status.setValue(new Status(task.getException().getMessage(),false));

                        }
                    }
                });
    }

    public void saveUserdata(String email, String userName) {


        SharedPreferences sh = appContext.getSharedPreferences(SharedPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();
        ed.putString(shared_Pref_Email,email);
        System.out.println("Entered user name ==== "+email + ">>>>" + userName );
        ed.putString(shared_Pref_userName,userName);
        ed.commit();
    }

}
