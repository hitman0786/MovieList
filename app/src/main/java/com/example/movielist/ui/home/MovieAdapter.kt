package com.example.movielist.ui.home

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.R
import com.example.movielist.model.Movie
import com.example.movielist.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.row_items_movie.view.*



class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieItem>() {

    var movieList: Array<Movie>? = null
    var context: Context? = null


    override fun onBindViewHolder(holder: MovieItem, position: Int) {

        if (movieList!!.isNotEmpty()) {

            val data = movieList!![position]
            holder.titleTV.text = String.format("%s",data.title)
            holder.releaseDateTV.text = String.format("%s", data.released)

          if(data.poster != null && data.poster != "N/A ") {

              val options = RequestOptions()

              Glide.with(context!!)
                  .load(data.poster)
                  .apply(options.
                      override(100, 100)
                      .placeholder(R.drawable.icon))
                  .into(holder.posterIV)
          }else{
              holder.posterIV.setImageResource(R.drawable.icon)
          }

            holder.movieCV.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("movie", data)
                context!!.startActivity(intent)
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

    inner class MovieItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterIV = itemView.posterIV
        val titleTV = itemView.titleTV
        val releaseDateTV = itemView.releaseDateTV
        val movieCV = itemView.movieCV
    }
}