package com.mmgoogleexpert.ptut.pmovie_tmdb.adapters

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.onTapMovie
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.inflate
import com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders.MovieViewHolder
import com.mmgoogleexpert.ptut.shared.ui.BaseAdapter

val diffUtil = object : DiffUtil.ItemCallback<MovieItem>(){
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}
class MovieAdapter(private val onTapMovie: onTapMovie):BaseAdapter<MovieViewHolder,MovieItem>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.view_movie_item),onTapMovie)
    }



}