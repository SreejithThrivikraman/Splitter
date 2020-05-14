package com.thrivikraman.sreejith.dev.splitter.adapters;



import com.thrivikraman.sreejith.dev.splitter.Fragments.onBoardScreen_1;
import com.thrivikraman.sreejith.dev.splitter.Fragments.onBoardScreen_2;
import com.thrivikraman.sreejith.dev.splitter.Fragments.onBoardScreen_3;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class onBoardingScreenAdapter extends FragmentPagerAdapter {


    public onBoardingScreenAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new onBoardScreen_1();
            case 1:
                return new onBoardScreen_2();
            case 2:
                return new onBoardScreen_3();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
