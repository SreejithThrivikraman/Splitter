package com.thrivikraman.sreejith.dev.splitter.views.userHome;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.thrivikraman.sreejith.dev.splitter.Global.GlobalApplication;
import com.thrivikraman.sreejith.dev.splitter.R;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UserHome extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static final String SharedPref = "SHARED_PREF";
    public static final String shared_Pref_Email = "SHARED_PREF-EMAIL";
    public static final String shared_Pref_userName = "SHARED_PREF-USERNAME";
    private Context appContext = GlobalApplication.getAppContext();
    private TextView userEmail,loggedUserName;
    private UserHomeViewModel UserHomeModel;
    private UserHomeViewModel userHomeVm;
    private FirebaseAuth currentUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
         NavigationView navigationView = findViewById(R.id.nav_view);

         //Setup Navigation Header
        View mnavigationHeader = navigationView.getHeaderView(0);
        userEmail = mnavigationHeader.findViewById(R.id.nav_Email);
        loggedUserName = mnavigationHeader.findViewById(R.id.loggedInUsername);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        UserHomeModel = ViewModelProviders.of(this).get(UserHomeViewModel.class);
        setupHeader();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    // Set the navigation Drawer Header.
    public void setupHeader()
    {
        UserHomeModel.fetchLoggedUserEmail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String loggedInUserEmail) {

                if(loggedInUserEmail != null ) {
                    userEmail.setText(loggedInUserEmail);
                }
                else {
                    userEmail.setText("Unknown User");
                }
            }
        });

        UserHomeModel.fetchLoggedUserName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String loggedInUserEmail) {

                if(loggedInUserEmail != null) {
                    loggedUserName.setText(loggedInUserEmail);
                } else {
                    loggedUserName.setText("Unknown User");
                }

            }
        });
    }

}
