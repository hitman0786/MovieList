package com.example.movielist.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielist.model.Movie
import com.example.movielist.model.MovieResponse
import com.example.movielist.repository.MovieRepository
import javax.inject.Inject

class HomeViewModel
@Inject constructor(private val dataRepo: MovieRepository): ViewModel(){

    //Show progress of API
    fun getProgress() = dataRepo.getProgress()

    //get Movies
    fun getMoviesList(): MutableLiveData<MovieResponse> = dataRepo.getMoviesList()

    //find error
    fun getErrors() = dataRepo.getErrors()

}