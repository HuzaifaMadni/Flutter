package com.huzaifa.daggerbeginner.dagger;


import com.huzaifa.daggerbeginner.MainActivity;
import com.huzaifa.daggerbeginner.car.Car;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {
                WheelsModule.class,
                PetrolEngineModule.class,
        })
public interface ActivityComponent {  // The Injector

    Car getCar();

    void inject(MainActivity mainActivity);

    /*@Subcomponent.Builder
    interface Builder {

        @BindsInstance
        Builder horsepower(@Named("horsepower") int horsepower);

        @BindsInstance
        Builder engineCapaity(@Named("engineCapacity") int engineCapacity);

        ActivityComponent build();
    }*/

    @Subcomponent.Factory
    interface Factory {

        ActivityComponent create(@BindsInstance @Named("horsepower") int horsepower,
                                 @BindsInstance @Named("engineCapacity") int engineCapacity);


    }

}
