package com.example.movielist.di

import com.example.movielist.ui.home.MainActivity
import com.example.movielist.ui.details.DetailsActivity
import com.example.movielist.ui.home.HomeActivity
import com.example.movielist.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activity module for
 * injecting activities
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
    @ContributesAndroidInjector
    abstract fun detailsActivity(): DetailsActivity
    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

}