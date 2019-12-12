package com.huzaifa.daggerbeginner.dagger;


import com.huzaifa.daggerbeginner.car.Rims;
import com.huzaifa.daggerbeginner.car.Tyres;
import com.huzaifa.daggerbeginner.car.Wheels;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class WheelsModule {

    @Provides
    static Rims provideRims(){
        return new Rims();
    }

    @Provides
    static Tyres provideTyre(){
        Tyres tyres = new Tyres();
        tyres.inflate();
        return tyres;
    }

    @Provides
    static Wheels provideWheels(Rims rims, Tyres tyres) {
        return new Wheels(rims, tyres);
    }
}
