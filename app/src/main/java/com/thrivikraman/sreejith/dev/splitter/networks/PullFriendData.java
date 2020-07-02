package com.thrivikraman.sreejith.dev.splitter.networks;
/* This class is used to pull the data related to the Friends of the User*/

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.thrivikraman.sreejith.dev.splitter.models.user;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class PullFriendData {

    private String userEmail;
    private double moneyOwe, moneyToBePaid;
    private ArrayList<String> friendNames = new ArrayList<>();
    private FirebaseAuth currentUserData;
    private final FirebaseUser user;
    private DatabaseReference ref;
    private firbaseConnectivity conn;

    // Class Constructor - pulls the logged in user email.
    public PullFriendData() {
        //this.userEmail = userEmail;

        this.conn = new firbaseConnectivity();
        this.ref = conn.getDatabasePath("Users");
        this.currentUserData = FirebaseAuth.getInstance();
        this.user = currentUserData.getCurrentUser();

        //System.out.println("Current User = " + user.getEmail());
    }


    public ArrayList<String> readData(final FirebaseCallBack callBack) {

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dp : dataSnapshot.getChildren()) {
                    if ((dp.child("Details/email").getValue().toString()).equals(user.getEmail())) {
                        DataSnapshot userKey = dp.child("Friends");

                        for ( DataSnapshot key : userKey.getChildren()) {
                            friendNames.add(key.child("Name").getValue().toString());
                        }
                    }
                }
                callBack.onCallBack(friendNames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  friendNames;
    }

    public interface FirebaseCallBack {
        ArrayList<String> onCallBack(ArrayList<String> list);
    }

    // function to get the total money that the friend owes from user.
    public double getMoneyOwes() {

        return moneyOwe;
    }

    // function to get the total money that is to be paid to the friend by the user.
    public double getMoneyToBePaid() {

        return moneyToBePaid;
    }
}
