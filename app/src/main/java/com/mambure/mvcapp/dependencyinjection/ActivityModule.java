package com.mambure.mvcapp.dependencyinjection;

import android.app.Activity;

import com.mambure.mvcapp.screens.common.MvcViewFactory;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class ActivityModule {

    @Provides
    public static MvcViewFactory providesMvcViewFactory(Activity activity) {
        return new MvcViewFactory(activity.getLayoutInflater());
    }
}