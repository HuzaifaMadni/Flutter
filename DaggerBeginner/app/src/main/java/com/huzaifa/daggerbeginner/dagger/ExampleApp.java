package com.huzaifa.daggerbeginner.dagger;

import android.app.Application;

public class ExampleApp extends Application {

    private AppComponent activityComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        activityComponent = DaggerAppComponent.factory().create(new DriverModule("Hans"));
    }

    public AppComponent getActivityComponent() {
        return activityComponent;
    }
}
