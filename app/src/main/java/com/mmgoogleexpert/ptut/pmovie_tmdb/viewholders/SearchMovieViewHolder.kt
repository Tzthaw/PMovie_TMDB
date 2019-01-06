package com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.onTapMovie
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.BASE_IMG_URL
import com.mmgoogleexpert.ptut.shared.model.BaseViewHolder
import kotlinx.android.synthetic.main.view_movie_item.view.*

class SearchMovieViewHolder(itemView:View,private val onTapMovie: onTapMovie): BaseViewHolder<MovieItem>(itemView){
    private lateinit var mMovieItem: MovieItem
    override fun setData(data: MovieItem) {
        mMovieItem=data
        Glide.with(itemView)
            .load("$BASE_IMG_URL${data.posterPath}")
//           .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
            .into(itemView.movieImage)
        itemView.movieTitle.text=data.title
    }


    override fun onClick(v: View?) {
        onTapMovie.onTapClick(mMovieItem,itemView.movieImage)
    }

}