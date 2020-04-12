package com.thrivikraman.sreejith.dev.splitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOptions extends AppCompatActivity {

    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);

        login = findViewById(R.id.login_button);
        signup= findViewById(R.id.signup_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginPage = new Intent(getApplicationContext(),Login.class);
                startActivity(loginPage);
            }
        });




    }


}
