package com.example.movielist.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.R
import com.example.movielist.model.Movie
import com.example.movielist.ui.details.DetailsActivity
import com.example.movielist.ui.home.fragment.HomeFragment
import com.example.movielist.ui.home.fragment.HomeFragmentDirections
import kotlinx.android.synthetic.main.row_items_movie.view.*
import javax.inject.Inject


class MovieAdapter(val fragment: HomeFragment): RecyclerView.Adapter<MovieAdapter.MovieItem>() {

    var movieList: Array<Movie>? = null
    var context: Context? = null
    @set:Inject
    var logo: Drawable? = null
    @set:Inject
    var requestManager: RequestManager? = null

    override fun onBindViewHolder(holder: MovieItem, position: Int) {

        if (movieList!!.isNotEmpty()) {

            val data = movieList!![position]
            holder.titleTV.text = String.format("%s",data.title)
            holder.releaseDateTV.text = String.format("%s", data.released)

          /*if(data.poster != null && data.poster != "N/A ") {

              val options = RequestOptions()

              Glide.with(context!!)
                  .load(data.poster)
                  .apply(options.
                      override(100, 100)
                      .placeholder(R.drawable.icon))
                  .into(holder.posterIV)
          }else{
              holder.posterIV.setImageResource(R.drawable.icon)
          }*/

            requestManager?.load(logo)?.into(holder.posterIV)

           holder.movieCV.setOnClickListener {
               fragment.showFullDataEvent(data);
           }
        }

    }



    /**
     * inflate row item layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItem {
        this.context = parent.context
        return MovieItem(LayoutInflater.from(parent.context).inflate(R.layout.row_items_movie, parent, false))
    }

    /**
     * set recyclerview list size
     */
    override fun getItemCount(): Int {
        return if (movieList != null) movieList!!.size else 0
    }


    /**
     * get updated list of transactions
     */
    fun setMovieData(itemList: Array<Movie>) {
        this.movieList = itemList
        notifyDataSetChanged()
    }

    inner class MovieItem(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val posterIV = itemView.posterIV
        val titleTV = itemView.titleTV
        val releaseDateTV = itemView.releaseDateTV
        val movieCV = itemView.movieCV
    }
}