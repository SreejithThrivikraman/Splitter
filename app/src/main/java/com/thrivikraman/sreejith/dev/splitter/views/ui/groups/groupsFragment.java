package com.thrivikraman.sreejith.dev.splitter.views.ui.groups;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrivikraman.sreejith.dev.splitter.GlobalApplication;
import com.thrivikraman.sreejith.dev.splitter.R;
import com.thrivikraman.sreejith.dev.splitter.views.ui.friends.FriendListAdapter;


public class groupsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public String[] myDataset = {"Group 1","Group 2","Group 3","Group 4","Group 5","Group 6","Group 7","Group 8","Group 9"};
    public GlobalApplication appContext;

    public groupsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_groups, container, false);

        Context ct = appContext.getAppContext();
        groupListAdapter friendAdapter = new groupListAdapter(ct,myDataset);

        recyclerView = (RecyclerView) root.findViewById(R.id.groupList);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(friendAdapter);
        return root;
    }
}