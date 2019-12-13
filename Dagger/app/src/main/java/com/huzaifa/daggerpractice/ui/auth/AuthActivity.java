package com.huzaifa.daggerpractice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.huzaifa.daggerpractice.R;
import com.huzaifa.daggerpractice.models.User;
import com.huzaifa.daggerpractice.viewmodels.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel authViewModel;
    private EditText userId;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authViewModel = new ViewModelProvider(this, viewModelFactory).get(AuthViewModel.class);
        btnLogin = findViewById(R.id.login_button);
        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);
        setLogo();
        subscribeObserver();

        btnLogin.setOnClickListener(this);
    }

    private void subscribeObserver(){

        authViewModel.observeUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    switch (userAuthResource.status){

                        case LOADING:{
                            handleProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED:{
                            handleProgressBar(false);
                            Log.d(TAG, "onChanged: Login Success" + (userAuthResource.data != null ? userAuthResource.data.getEmail() : null));
                            break;
                        }
                        case ERROR:{
                            handleProgressBar(false);
                            Toast.makeText(AuthActivity.this, "Error occurred. Please enter a number between 1 and 10", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            handleProgressBar(false);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void handleProgressBar(boolean isVisible){
        if (isVisible){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:{
                attemptLogin();
                break;
            }
        }
    }

    private void attemptLogin() {
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        } else {
            authViewModel.authenticateWithID(Integer.parseInt(userId.getText().toString().trim()));
        }
    }
}
