package com.thrivikraman.sreejith.dev.splitter;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class onBoarding extends AppCompatActivity
{

    ViewPager introScreens;
    TabLayout dots;
    int number_of_pages = 0;
    int max_onBoardingScreens = 2;
    Button skip, next, back;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        next = findViewById(R.id.next_button);
        back = findViewById(R.id.back_button);
        dots = findViewById(R.id.tab_layout);
        introScreens = findViewById(R.id.onBoardScreen);


        onBoardingScreenAdapter adapter = new onBoardingScreenAdapter(getSupportFragmentManager());
        introScreens.setAdapter(adapter);

        dots.setupWithViewPager(introScreens, true);

        introScreens.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1)
            {
                if(i == max_onBoardingScreens)
                {
                    next.setText("Finish");
                }
                else
                {
                    next.setText("Next");
                }

                if(i == 0)
                {
                    back.setVisibility(View.INVISIBLE);
                }else {
                    back.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_of_pages = introScreens.getCurrentItem();

                if (number_of_pages == max_onBoardingScreens) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                } else if (number_of_pages == max_onBoardingScreens - 1) {
                    next.setText("Finish");
                    introScreens.setCurrentItem(number_of_pages + 1);
                } else {
                    introScreens.setCurrentItem(number_of_pages + 1);
                }
            }
        });


    }
}


