package com.huzaifa.daggerbeginner.car;

import android.util.Log;

import com.huzaifa.daggerbeginner.dagger.PerActivity;

import javax.inject.Inject;

@PerActivity
public class Car {

    private static final String TAG = "Car";

    private Driver driver;
    private Engine engine;
    private Wheels wheels;

    @Inject
    public Car(Driver driver, Engine engine, Wheels wheels) {
        this.driver = driver;
        this.engine = engine;
        this.wheels = wheels;
    }

    public void Drive(){
        engine.start();
        Log.d(TAG, "Drive: " + driver.name + " is driving....." + this);
    }

    @Inject
    public void enableRemote(Remote remote){
        remote.setListener(this);
    }
}
