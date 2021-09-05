package com.mambure.mvcapp.dependencyinjection;

import android.app.Activity;

import com.mambure.mvcapp.screens.common.MvcViewFactory;
import com.mambure.mvcapp.screens.common.MyNavigator;
import com.mambure.mvcapp.screens.common.ToastManager;

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

    @Provides
    public static MyNavigator providesNavigator(Activity activity) {
        return new MyNavigator(activity);
    }

    @Provides
    public static ToastManager providesToastManager(Activity activity) {
        return new ToastManager(activity);
    }
}