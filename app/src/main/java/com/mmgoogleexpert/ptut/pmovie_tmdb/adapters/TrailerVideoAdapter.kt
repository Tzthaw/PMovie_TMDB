package com.mmgoogleexpert.ptut.pmovie_tmdb.adapters

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.OnTapTrailer
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.ReviewItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.TrailerItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.inflate
import com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders.ReviewViewHolder
import com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders.TrailerViewHolder
import com.mmgoogleexpert.ptut.shared.ui.BaseAdapter

val trailerDiffUtil = object : DiffUtil.ItemCallback<TrailerItem>(){
    override fun areItemsTheSame(oldItem: TrailerItem, newItem: TrailerItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: TrailerItem, newItem: TrailerItem): Boolean {
        return oldItem == newItem
    }
}

class TrailerVideoAdapter(private val onTapTrailer: OnTapTrailer): BaseAdapter<TrailerViewHolder, TrailerItem>(trailerDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        return TrailerViewHolder(parent.inflate(R.layout.item_video),onTapTrailer)
    }
}