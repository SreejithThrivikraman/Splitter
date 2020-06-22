package com.thrivikraman.sreejith.dev.splitter.views;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thrivikraman.sreejith.dev.splitter.GlobalApplication;
import com.thrivikraman.sreejith.dev.splitter.R;
import com.thrivikraman.sreejith.dev.splitter.databinding.ActivityLoginBinding;
import com.thrivikraman.sreejith.dev.splitter.models.Status;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import com.thrivikraman.sreejith.dev.splitter.viewModels.LoginViewModel;
import java.util.Objects;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class Login extends AppCompatActivity {


    TextView text_passwordReset;
    String string_passwordReset = "<u>Forgot your Password ?</u>";
    private LoginViewModel LoginModel;
    private ActivityLoginBinding binding;
    private boolean authenticationSuccess = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text_passwordReset = findViewById(R.id.passwordReset);
        text_passwordReset.setText(Html.fromHtml(string_passwordReset));

        LoginModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLoginModel(LoginModel);

        LoginModel.getUserInfo().observe(this, new Observer<user>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onChanged(@Nullable user user) {
//                 if (TextUtils.isEmpty(Objects.requireNonNull(user).getEmail())) {
//                    binding.emailField.setError("Enter an Email address");
//                    binding.emailField.requestFocus();
//                } else
                  if (!user.validateEmailAddress()) {
                    binding.emailField.setError("Enter a Valid E-mail Address");
                    binding.emailField.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getPassword())) {
                    binding.passwordField.setError("Enter the password !");
                    binding.passwordField.requestFocus();
                } else {
                     user loginUser = new user(user.getEmail(),user.getPassword());
                     LoginModel.authenticateUser(loginUser);
                }

            }
        });

        LoginModel.isBackButtonPressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent IntentLoginOptions = new Intent(getApplicationContext(),LoginOptions.class);
                startActivity(IntentLoginOptions);
            }
        });

        LoginModel.updateLoginInStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(Status Status) {
                if (Status.isFlag()) {
                    Toast.makeText(getApplicationContext(), "Login in Success !", Toast.LENGTH_LONG).show();
                    Intent IntentHome = new Intent(getApplicationContext(),UserHome.class);
                    startActivity(IntentHome);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                    alertDialog.setTitle("Oops..");
                    alertDialog.setMessage(Status.getMessage());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

    }


}
