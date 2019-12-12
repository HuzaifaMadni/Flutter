package com.huzaifa.daggerbeginner.dagger;


import com.huzaifa.daggerbeginner.car.Engine;
import com.huzaifa.daggerbeginner.car.PetrolEngine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PetrolEngineModule {

    @Binds
    abstract Engine bindEngine(PetrolEngine petrolEngine);
}
