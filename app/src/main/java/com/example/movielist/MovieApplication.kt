package com.example.movielist

import android.app.Activity
import android.app.Application
import com.example.movielist.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MovieApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        /**
         * added dagger2 in application class
         */
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}