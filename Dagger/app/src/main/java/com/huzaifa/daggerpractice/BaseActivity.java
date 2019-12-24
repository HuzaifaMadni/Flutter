package com.huzaifa.daggerpractice;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.huzaifa.daggerpractice.models.User;
import com.huzaifa.daggerpractice.ui.auth.AuthActivity;
import com.huzaifa.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        subscribeObserver();
    }

    private void subscribeObserver(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null){
                    switch (userAuthResource.status){

                        case LOADING:{
                            break;
                        }
                        case AUTHENTICATED:{
                            Log.d(TAG, "onChanged: Login Success" + (userAuthResource.data != null ? userAuthResource.data.getEmail() : null));
                            break;
                        }
                        case ERROR:{
                            Toast.makeText(BaseActivity.this, "Error occurred. Please enter a number between 1 and 10", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            navLoginScreen();
                            break;
                        }
                    }
                }
            }
        });
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
