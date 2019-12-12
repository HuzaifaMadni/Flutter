package com.huzaifa.daggerbeginner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.huzaifa.daggerbeginner.car.Car;
import com.huzaifa.daggerbeginner.dagger.ActivityComponent;
import com.huzaifa.daggerbeginner.dagger.AppComponent;
import com.huzaifa.daggerbeginner.dagger.DieselEngineModule;
import com.huzaifa.daggerbeginner.dagger.ExampleApp;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Car car1, car2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityComponent activityComponent = (((ExampleApp) getApplication()).getActivityComponent())
                .getActivityComponentFactory()
                .create(120,450);

        activityComponent.inject(this);

        car1.Drive();

        car2.Drive();
    }
}
