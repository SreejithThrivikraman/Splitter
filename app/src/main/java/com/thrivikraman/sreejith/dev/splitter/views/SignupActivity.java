package com.thrivikraman.sreejith.dev.splitter.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.thrivikraman.sreejith.dev.splitter.R;
import com.thrivikraman.sreejith.dev.splitter.databinding.ActivitySignupBinding;
import com.thrivikraman.sreejith.dev.splitter.models.expenses;
import com.thrivikraman.sreejith.dev.splitter.models.loginStatus;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import com.thrivikraman.sreejith.dev.splitter.viewModels.SignInViewModel;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    private SignInViewModel SignInModel;
    private ActivitySignupBinding bindingSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SignInModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        bindingSignUp = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        bindingSignUp.setLifecycleOwner(this);
        bindingSignUp.setSignInModel(SignInModel);

        SignInModel.getUserdata().observe(this, new Observer<user>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onChanged(user user) {
                if (TextUtils.isEmpty(Objects.requireNonNull(user).getFullName())) {
                    bindingSignUp.fullNameField.setError("Enter the full name !");
                    bindingSignUp.fullNameField.requestFocus();
//                } else if (!user.validateEmailAddress()) {
//                    bindingSignUp.emailField.setError("Enter a Valid E-mail Address");
//                    bindingSignUp.emailField.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getEmail())) {
                    bindingSignUp.emailField.setError("Enter an Email address");
                    bindingSignUp.emailField.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getPassword())) {
                    bindingSignUp.passwordField.setError("Enter the password !");
                    bindingSignUp.passwordField.requestFocus();
                } else {
                    user newUser = new user(user.getFullName(), user.getEmail(),user.getPassword(),user.getPhoneNumber());
                    expenses default_user = new expenses("null","null","null","null","null",0);
                    SignInModel.createUser(newUser, default_user);
                }
            }
        });

        SignInModel.isBackButtonPressed().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent IntentLoginOptions = new Intent(getApplicationContext(),LoginOptions.class);
                startActivity(IntentLoginOptions);
            }
        });

        SignInModel.updateSignInStatus().observe(this, new Observer<loginStatus>() {
            @Override
            public void onChanged(loginStatus loginStatus) {
                if (loginStatus.isFlag() == true) {
                    Toast.makeText(getApplicationContext(), "Sign Up Success !", Toast.LENGTH_LONG).show();
                    Intent IntentHome = new Intent(getApplicationContext(),Home.class);
                    startActivity(IntentHome);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(SignupActivity.this).create();
                    alertDialog.setTitle("Oops..");
                    alertDialog.setMessage(loginStatus.getMessage());
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
