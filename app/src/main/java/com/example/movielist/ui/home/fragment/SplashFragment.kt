package com.example.movielist.ui.home.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.movielist.R
import dagger.android.support.AndroidSupportInjection



class SplashFragment : Fragment() {

    private val SPLASH_TIME_OUT = 3000

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Handler().postDelayed(
            Runnable
            {
                NavHostFragment.findNavController(this).navigate(R.id.homeFragment)

            }, SPLASH_TIME_OUT.toLong()
        )
    }


}
