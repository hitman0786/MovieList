package com.example.movielist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Current Movie Response class
 */

@Parcelize
data class MovieResponse(@SerializedName("movies") val movies:Array<Movie>): Parcelable

@Parcelize
data class Movie(
    @SerializedName("Title") val title:String,
    @SerializedName("Year") val year:String,
    @SerializedName("Rated") val rated:String,
    @SerializedName("Released") val released:String,
    @SerializedName("Runtime") val runtime:String,
    @SerializedName("Genre") val genre:String,
    @SerializedName("Director") val director:String,
    @SerializedName("Writer") val writer:String,
    @SerializedName("Actors") val actors:String,
    @SerializedName("Plot") val plot:String,
    @SerializedName("Language") val language:String,
    @SerializedName("Country") val country:String,
    @SerializedName("Awards") val awards:String,
    @SerializedName(value = "Poster ", alternate = ["Poster"]) val poster: String?

): Parcelable
