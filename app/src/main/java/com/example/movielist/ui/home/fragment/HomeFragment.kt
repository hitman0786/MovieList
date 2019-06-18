package com.example.movielist.ui.home.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.movielist.R
import com.example.movielist.model.Movie
import com.example.movielist.ui.home.HomeViewModel
import com.example.movielist.ui.home.MovieAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    var movieAdapter: MovieAdapter? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //add adapter to recyclerview
        listRV.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        movieAdapter = MovieAdapter(this)
        listRV.adapter = movieAdapter

        //Call viewmodel to access data from data source
        observeData()
        observeProgress()
        observeError()
    }


    //check for any errors
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

    private fun Boolean?.getVisibility(): Int = if (this != null && this) View.VISIBLE else View.GONE

    //send data to details fragment
     fun showFullDataEvent(data: Movie){
        val action =  HomeFragmentDirections.actionHomeFragmentToDetailsFragment(data)
        NavHostFragment.findNavController(this).navigate(action)
    }

}
