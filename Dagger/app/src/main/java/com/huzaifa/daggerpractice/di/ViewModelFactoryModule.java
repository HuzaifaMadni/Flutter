package com.huzaifa.daggerpractice.di;

import androidx.lifecycle.ViewModelProvider;


import com.huzaifa.daggerpractice.viewmodels.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
