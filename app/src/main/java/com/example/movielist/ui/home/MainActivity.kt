package com.example.movielist.ui.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.movielist.R
import com.example.movielist.base.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    var movieAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        //add adapter to recyclerview
        /*listRV.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        movieAdapter = MovieAdapter()
        listRV.adapter = movieAdapter*/

        //Call viewmodel to access data from data source
        /*observeData()
        observeProgress()
        observeError()*/
    }

    /*//check for any errors
    private fun observeError() {
        mViewModel.getErrors().observe(this, Observer {
            it?.let {
                nodataFoundTV.visibility = View.VISIBLE
                listRV.visibility = View.GONE
                nodataFoundTV.text = it
            }
        })
    }

    //show progress
    private fun observeProgress() {
        mViewModel.getProgress().observe(this, Observer {
            progressBar.visibility = it.getVisibility()
        })
    }

    private fun observeData() {
        mViewModel.getMoviesList().observe(this, Observer {
            it?.let {
                if(it.movies.isNotEmpty()) movieAdapter!!.setMovieData(it.movies)
                nodataFoundTV.visibility = View.GONE
                listRV.visibility = View.VISIBLE
            }
        })
    }
*/
    private fun Boolean?.getVisibility(): Int = if (this != null && this) View.VISIBLE else View.GONE
}
