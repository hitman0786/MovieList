package com.example.movielist.di

import com.example.movielist.ui.home.fragment.DetailsFragment
import com.example.movielist.ui.home.fragment.HomeFragment
import com.example.movielist.ui.home.fragment.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FrgamentModule {

    @ContributesAndroidInjector
    abstract fun splashFragment(): SplashFragment
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
    @ContributesAndroidInjector
    abstract fun detailsFragment(): DetailsFragment
}