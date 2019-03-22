package com.example.movielist.ui.details

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.R
import com.example.movielist.base.BaseActivity
import com.example.movielist.model.Movie
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_details)

        val movie = intent.getSerializableExtra("movie") as Movie

        if(movie.poster != null && movie.poster != "N/A ") {

            val options = RequestOptions()

            Glide.with(this)
                .load(movie.poster)
                .apply(options.
                    override(500, 500)
                    .placeholder(R.drawable.icon))
                .into(posterIV)
        }else{
            posterIV.setImageResource(R.drawable.icon)
        }


        titleTV.text = movie.title
        directorTV.text = movie.director
        writerTV.text = movie.writer
        actorsTV.text = movie.actors
        plotTV.text = movie.plot
        languageTV.text = movie.language
        countryTV.text = movie.country
        genreTV.text = movie.genre
        releasedTV.text = movie.released
        runtimeTV.text = movie.runtime
        awardsTV.text = movie.awards
    }
}