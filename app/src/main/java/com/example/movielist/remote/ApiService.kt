package com.example.movielist.remote

import com.example.movielist.model.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit2 api service interface
 * define api end points
 */

interface ApiService {

    @GET("itzx2")
    fun getMoviesList(): Deferred<Response<MovieResponse>>
}