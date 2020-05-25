package com.thrivikraman.sreejith.dev.splitter.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.thrivikraman.sreejith.dev.splitter.R;
import com.thrivikraman.sreejith.dev.splitter.databinding.ActivitySignupBinding;
import com.thrivikraman.sreejith.dev.splitter.models.expenses;
import com.thrivikraman.sreejith.dev.splitter.models.user;
import com.thrivikraman.sreejith.dev.splitter.networks.firbaseConnectivity;
import com.thrivikraman.sreejith.dev.splitter.viewModels.SignInViewModel;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    private SignInViewModel SignInModel;
    private ActivitySignupBinding bindingSignUp;
    private boolean firebaseQueryflag = false;


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
                    Toast.makeText(getApplicationContext(), "Sign Up Success !", Toast.LENGTH_LONG).show();

                    user SampleUser = new user("Sreejith Thrivikraman", "mail.sreejith.23@gmail.com","123456789","****");
                    expenses default_user = new expenses("null","null","null","null","null",0);
                    firebaseQueryflag = SignInModel.createUser(SampleUser, default_user);

                    if(firebaseQueryflag) {
                        Intent IntentHome = new Intent(getApplicationContext(),Home.class);
                        startActivity(IntentHome);
                    }
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
    }
}
