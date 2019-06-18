package com.example.movielist.di.component

import android.app.Application
import com.example.movielist.MovieApplication
import com.example.movielist.di.ActivityModule
import com.example.movielist.di.FrgamentModule
import com.example.movielist.di.NetworkModule
import com.example.movielist.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [NetworkModule::class,
    ViewModelModule::class,
    ActivityModule::class,
    FrgamentModule::class,
    AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


    fun inject(appController: MovieApplication)
}