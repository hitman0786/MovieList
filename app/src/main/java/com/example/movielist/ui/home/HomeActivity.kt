package com.example.movielist.ui.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movielist.R
import com.example.movielist.base.BaseActivity
import com.example.movielist.model.Movie
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HomeActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        //navController = Navigation.findNavController(this, R.id.navigationHostFragment)
        //observeData()
    }


    //check for any errors
    private fun observeError() {
        mViewModel.getErrors().observe(this, Observer {
            it?.let {

            }
        })
    }

    //show progress
    private fun observeProgress() {
        mViewModel.getProgress().observe(this, Observer {

        })
    }

    private fun observeData() {
        mViewModel.getMoviesList().observe(this, Observer {
            it?.let {
                if(it.movies.isNotEmpty()) {
                    for(movie: Movie in it.movies){
                        Log.e("TAG",movie.title)
                    }
                    movieList = it.movies
                }
            }
        })
    }

    companion object{
        var movieList: Array<Movie>? = null
    }

    override fun onSupportNavigateUp(): Boolean {
        return false//navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}