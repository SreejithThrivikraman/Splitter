package com.thrivikraman.sreejith.dev.splitter.views.ui.friends;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thrivikraman.sreejith.dev.splitter.GlobalApplication;
import com.thrivikraman.sreejith.dev.splitter.R;
import com.thrivikraman.sreejith.dev.splitter.networks.PullFriendData;
import com.thrivikraman.sreejith.dev.splitter.viewModels.LoginViewModel;

import java.util.ArrayList;


public class FriendsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FriendsViewModel FriendsVM;
    public ArrayList<String> myDataset = new ArrayList<>();
    //public String[] myDataset = {"reay","goofy", "erick","Sreejith","Jones","Simran","Damien","Damien","Damien"};
    public GlobalApplication gb;
    private Button addFriends;

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_friends, container, false);

        final Context ct = gb.getAppContext();
        FriendsVM = ViewModelProviders.of(this).get(FriendsViewModel.class);

        // Pulling Friends List.
        FriendsVM.pullFriendList();

        recyclerView = (RecyclerView) root.findViewById(R.id.friendList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Observing FriendLists.
        FriendsVM.getFriendList().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                FriendListAdapter friendAdapter = new FriendListAdapter(ct,strings);
                recyclerView.setAdapter(friendAdapter);
            }
        });


        return root;




    }
}