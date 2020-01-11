package com.example.movielist.ui.home.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import com.example.movielist.model.MovieResponse
import com.example.movielist.ui.home.HomeActivity
import com.example.movielist.ui.home.HomeViewModel
import com.example.movielist.ui.home.MovieAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[HomeViewModel::class.java]
    }

    var movieAdapter: MovieAdapter? = null
    var itemList: Array<Movie>? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        observeData()

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //add adapter to recyclerview
        listRV.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        movieAdapter = MovieAdapter(this)
       // movieAdapter!!.setMovieData(HomeActivity.movieList!!)
        listRV.adapter = movieAdapter
       // listRV.visibility = View.VISIBLE
        //progressBar.visibility = View.GONE


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        //Call viewmodel to access data from data source
       // observeData()

        mViewModel.getMoviesList().value?.apply(this::setObserveData)
      //  observeProgress()
        //observeError()

    }


    //check for any errors
    private fun observeError() {
        mViewModel.getErrors().observe(viewLifecycleOwner, Observer {
            it?.let {
                nodataFoundTV.visibility = View.VISIBLE
                listRV.visibility = View.GONE
                nodataFoundTV.text = it
            }
        })
    }

    //show progress
    private fun observeProgress() {
        mViewModel.getProgress().observe(viewLifecycleOwner, Observer {
            progressBar.visibility = it.getVisibility()
        })
    }

    private fun observeData() {
        mViewModel.getMoviesList().observe(this, Observer {
            it?.let {
                if(it.movies.isNotEmpty()) itemList = it.movies

                mViewModel.getMoviesList().value?.apply(this::setObserveData)
            }
        })
    }

    private fun setObserveData(response: MovieResponse?) {
        if (response != null){
            val list = response.movies
            movieAdapter!!.setMovieData(list)
            nodataFoundTV.visibility = View.GONE
            progressBar.visibility = View.GONE
            listRV.visibility = View.VISIBLE
        }

    }

    private fun Boolean?.getVisibility(): Int = if (this != null && this) View.VISIBLE else View.GONE

    //send data to details fragment
     fun showFullDataEvent(data: Movie){
        val action =  HomeFragmentDirections.actionHomeFragmentToDetailsFragment(data)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Test","onStop");
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Test","onDestroy");
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Test","onDestroyView");
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Test","onDetach");
    }

}
