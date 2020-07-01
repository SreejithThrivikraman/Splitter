package com.thrivikraman.sreejith.dev.splitter.views.ui.friends;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrivikraman.sreejith.dev.splitter.GlobalApplication;
import com.thrivikraman.sreejith.dev.splitter.R;


public class FriendsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public String[] myDataset = {"reay","goofy", "erick","Sreejith","Jones","Simran","Damien","Damien","Damien"};
    public GlobalApplication gb;

    public FriendsFragment() {
        // Required empty public constructor



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_friends, container, false);

        Context ct = gb.getAppContext();
        FriendListAdapter friendAdapter = new FriendListAdapter(ct,myDataset);

        recyclerView = (RecyclerView) root.findViewById(R.id.friendList);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(friendAdapter);
        return root;
    }
}