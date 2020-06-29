package com.thrivikraman.sreejith.dev.splitter.adapters;


import com.thrivikraman.sreejith.dev.splitter.views.ui.friends.FriendsFragment;
import com.thrivikraman.sreejith.dev.splitter.views.ui.groups.groupsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeViewPagerAdapter extends FragmentStateAdapter {

    private int numOfPages;

    public HomeViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int numOfPages) {
        super(fragmentActivity);
        this.numOfPages = numOfPages;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0 : return new FriendsFragment();
            case 1 : return new groupsFragment();
            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return numOfPages;
    }
}
