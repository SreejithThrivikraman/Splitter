package com.thrivikraman.sreejith.dev.splitter.viewModels;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import com.thrivikraman.sreejith.dev.splitter.networks.firbaseConnectivity;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NavigationHeaderViewModel extends ViewModel {

    public MutableLiveData<String> FullName = new MutableLiveData<>();
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<user> userMutableLiveData = new MutableLiveData<>();
    private FirebaseAuth currentUserData;
    private String TAG = "Debug : UserHomeViewModel";

    firbaseConnectivity conn = new firbaseConnectivity();
    DatabaseReference ref = conn.getDatabasePath("Users");

    // return loggedInUserEmail
    public MutableLiveData<String> fetchLoggedUserEmail()
    {
        currentUserData = FirebaseAuth.getInstance();
        final FirebaseUser user = currentUserData.getCurrentUser();
        emailAddress.setValue(user.getEmail());
        return  emailAddress;
    }

    // return loggedInUserName
    public MutableLiveData<String> fetchLoggedUserName()
    {
        currentUserData = FirebaseAuth.getInstance();
        final FirebaseUser user = currentUserData.getCurrentUser();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for( DataSnapshot dp : dataSnapshot.getChildren())
                {
                    if((dp.child("Details/email").getValue().toString()).equals(user.getEmail())) {
                        FullName.setValue(dp.child("Details/fullName").getValue().toString());
                        // apply local storage of name and email
                        break;
                    } else {
                        FullName.setValue("Unknown User");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });
        return FullName;
    }
}
