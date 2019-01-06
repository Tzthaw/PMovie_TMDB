package com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.mmgoogleexpert.ptut.listitemcodelab.viewholders.BaseViewHolder
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.onTapMovie
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import kotlinx.android.synthetic.main.view_movie_item.view.*

class MovieViewHolder(itemView:View,private val onTapMovie: onTapMovie):BaseViewHolder<MovieItem>(itemView) {

    private val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500"
    private lateinit var mMovieItem:MovieItem
    override fun bindData(item: MovieItem) {
        super.bindData(item)
        mMovieItem=item
        Log.e("image","$IMAGE_BASE_URL${item.posterPath}")
//        ImageRequester.setImageFromUrl(
//            itemView.movieImage,
//            "http://image.tmdb.org/t/p/w500/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg"
//        )
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w500/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg")
//           .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
            .into(itemView.movieImage)
        itemView.movieTitle.text=item.title
    }
    override fun onClick(v: View?) {
        onTapMovie.onTapClick(mMovieItem)
    }
}