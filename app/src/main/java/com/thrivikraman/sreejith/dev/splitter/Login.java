package com.thrivikraman.sreejith.dev.splitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    TextView text_passwordReset;
    String string_passwordReset = "<u>Forgot your Password ?</u>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text_passwordReset = findViewById(R.id.passwordReset);

        text_passwordReset.setText(Html.fromHtml(string_passwordReset));
    }
}
