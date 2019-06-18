package com.example.movielist.di

import androidx.lifecycle.ViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import androidx.lifecycle.ViewModelProvider
import com.example.movielist.di.factory.ViewModelFactory
import com.example.movielist.ui.home.HomeViewModel
import dagger.Module

/**
 * ViewModel module class
 * Bind all view models here
 * and also bind view model factory
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun postHomeViewModel(viewModel: HomeViewModel): ViewModel
}