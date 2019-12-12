package com.huzaifa.daggerbeginner.car;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

public class PetrolEngine implements Engine {

    private static final String TAG = "Car";

    private int horsePower;
    private int engineCapacity;

    @Inject
    public PetrolEngine(@Named("horsepower") int horsePower,
                        @Named("engineCapacity") int engineCapacity) {
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public void start() {
        Log.d(TAG, "start: Petrol Engine Started. \nHorsePower: " + horsePower + "\nEngineCapacity: " + engineCapacity);
    }
}
