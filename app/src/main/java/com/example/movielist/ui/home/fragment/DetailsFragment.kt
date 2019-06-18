package com.example.movielist.ui.home.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.R
import com.example.movielist.model.Movie
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie: Movie? = arguments?.getParcelable("movie")

        val check = movie != null ?: false
        if(check) {
            if (movie?.poster != "N/A ") {

                val options = RequestOptions()

                Glide.with(this)
                    .load(movie?.poster)
                    .apply(
                        options.override(500, 500)
                            .placeholder(R.drawable.icon)
                    )
                    .into(posterIV)
            } else {
                posterIV.setImageResource(R.drawable.icon)
            }


            titleTV.text = movie?.title ?: ""
            directorTV.text = movie?.director
            writerTV.text = movie?.writer
            actorsTV.text = movie?.actors
            plotTV.text = movie?.plot
            languageTV.text = movie?.language
            countryTV.text = movie?.country
            genreTV.text = movie?.genre
            releasedTV.text = movie?.released
            runtimeTV.text = movie?.runtime
            awardsTV.text = movie?.awards

        }else{
            Toast.makeText(activity, "Something going wrong....", Toast.LENGTH_LONG).show()
        }
    }

}
