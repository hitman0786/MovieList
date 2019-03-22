package com.example.movielist.repository


import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.movielist.base.BaseActivity
import com.example.movielist.model.MovieResponse
import com.example.movielist.remote.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implement API call
 */
class MovieRepository

@Inject constructor(private val apiService: ApiService){

    private val progress = MutableLiveData<Boolean>()
    private val error = MutableLiveData<String>()
    val data = MutableLiveData<MovieResponse>()

    fun getProgress() = progress
    fun getErrors() = error

    fun getMoviesList(): MutableLiveData<MovieResponse> {

        progress.postValue(true)

        if(BaseActivity.isInternetConnected) {

            GlobalScope.launch {
                try {

                    val request = apiService.getMoviesList()
                    val response = request.await()

                    if(response.isSuccessful){
                        progress.postValue(false)
                        data.postValue(response.body())
                    }else{
                        progress.postValue(false)
                        Log.e("Error",response.message())
                        error.postValue(response.message())
                    }

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }else{
            progress.postValue(false)
            error.postValue("It seems internet is slow or disconnected!")
        }

        return data
    }


}