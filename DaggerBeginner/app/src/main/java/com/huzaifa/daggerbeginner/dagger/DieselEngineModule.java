package com.huzaifa.daggerbeginner.dagger;


import com.huzaifa.daggerbeginner.car.DieselEngine;
import com.huzaifa.daggerbeginner.car.Engine;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class DieselEngineModule {

    private int horsepower;

    public DieselEngineModule(int horsepower) {
        this.horsepower = horsepower;
    }

    @Provides
    int provideHorsePower(){
        return horsepower;
    }

    @Provides
    Engine provideEngine(DieselEngine engine){
        return engine;
    }
}
