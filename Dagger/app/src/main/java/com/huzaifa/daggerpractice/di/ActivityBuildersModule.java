package com.huzaifa.daggerpractice.di;

import com.huzaifa.daggerpractice.di.auth.AuthModule;
import com.huzaifa.daggerpractice.di.auth.AuthViewModelsModule;
import com.huzaifa.daggerpractice.di.main.MainFragmentBuildersModule;
import com.huzaifa.daggerpractice.ui.auth.AuthActivity;
import com.huzaifa.daggerpractice.ui.auth.AuthViewModel;
import com.huzaifa.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
                    AuthModule.class,
    })
    abstract AuthActivity contributesAuthActivity();

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class
            }
    )
    abstract MainActivity contributesMainActivity();

}
