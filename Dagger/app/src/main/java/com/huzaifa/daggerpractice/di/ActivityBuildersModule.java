package com.huzaifa.daggerpractice.di;

import com.huzaifa.daggerpractice.di.auth.AuthViewModelsModule;
import com.huzaifa.daggerpractice.ui.auth.AuthActivity;
import com.huzaifa.daggerpractice.ui.auth.AuthViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = { AuthViewModelsModule.class
    })
    abstract AuthActivity contributesAuthActivity();

}
