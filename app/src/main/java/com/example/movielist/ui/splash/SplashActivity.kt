package com.example.movielist.ui.splash

import android.os.Bundle
import com.example.movielist.R
import com.example.movielist.base.BaseActivity
import dagger.android.AndroidInjection
import com.example.movielist.ui.home.MainActivity
import android.content.Intent
import android.os.Handler


class SplashActivity: BaseActivity() {

    private val SPLASH_TIME_OUT = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_spalsh)

        Handler().postDelayed(
            Runnable
            {
                // This method will be executed once the timer is over
                // Start your app main activity
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)

                // close this activity
                finish()
            }, SPLASH_TIME_OUT.toLong()
        )

    }
}