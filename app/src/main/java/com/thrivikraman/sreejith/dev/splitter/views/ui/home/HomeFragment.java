package com.thrivikraman.sreejith.dev.splitter.views.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.thrivikraman.sreejith.dev.splitter.R;
import com.thrivikraman.sreejith.dev.splitter.adapters.HomeViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager2 vp2;
    private TabLayout tab;
    private TabItem friends,groups;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
      
        vp2 = root.findViewById(R.id.homeViewPager);
        tab = root.findViewById(R.id.tabLayout);

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        vp2.setAdapter(new HomeViewPagerAdapter(getActivity(),tab.getTabCount()));
        new TabLayoutMediator(tab, vp2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case 0 : tab.setText("Friends"); break;
                            case 1 : tab.setText("Groups"); break;
                            default: tab.setText("No Name");
                        }
                    }
                }
        ).attach();

    }
}
