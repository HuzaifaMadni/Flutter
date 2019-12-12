package com.huzaifa.daggerbeginner.dagger;

import com.huzaifa.daggerbeginner.car.Driver;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        DriverModule.class
})
public interface AppComponent {

    ActivityComponent.Factory getActivityComponentFactory();

    @Component.Factory
    interface Factory {

        AppComponent create(DriverModule driverModule);
    }
}
