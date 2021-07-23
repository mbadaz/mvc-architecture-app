package com.mambure.mvcapp.dependencyinjection;

import android.app.Activity;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class ActivityModule {

    @Provides
    LayoutInflater providesLayoutInflater(Activity activity) {
        return activity.getLayoutInflater();
    }
}
