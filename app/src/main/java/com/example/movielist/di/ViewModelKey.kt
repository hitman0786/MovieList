package com.example.movielist.di

import android.arch.lifecycle.ViewModel

import dagger.MapKey
import kotlin.reflect.KClass

/**
 * View model key for
 * mapping view models
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
