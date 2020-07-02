package com.thrivikraman.sreejith.dev.splitter.views.ui.friends;

import com.thrivikraman.sreejith.dev.splitter.networks.PullFriendData;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> friendsList;

    public FriendsViewModel() {
        friendsList = new MutableLiveData<>();
       // mText.setValue("This is home fragment");
    }

    public MutableLiveData<ArrayList<String>> getFriendList() {
        return friendsList;
    }


    public MutableLiveData<ArrayList<String>> pullFriendList()
    {
        PullFriendData fd = new PullFriendData();
        fd.readData(new PullFriendData.FirebaseCallBack() {
            @Override
            public ArrayList<String> onCallBack(ArrayList<String> list) {
                System.out.println("<<<<<< Live data <<<<" + list);
                friendsList.setValue(list);
                return null;
            }
        });
        return friendsList;
    }


}

