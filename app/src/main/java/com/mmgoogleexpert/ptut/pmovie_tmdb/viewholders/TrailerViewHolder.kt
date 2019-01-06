package com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.OnTapTrailer
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.TrailerItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.YOUTUBE_THUMBNAIL_URL
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.YOUTUBE_VIDEO_URL
import com.mmgoogleexpert.ptut.shared.ui.BaseViewHolder
import kotlinx.android.synthetic.main.item_video.view.*

class TrailerViewHolder(itemView:View,private val onTapTrailer: OnTapTrailer):BaseViewHolder<TrailerItem>(itemView) {
   private lateinit var trailerItem: TrailerItem
    override fun bindData(item: TrailerItem) {
        super.bindData(item)
        trailerItem=item
        itemView.itemVideoTitle.text=item.name
        Glide.with(itemView.itemVideoCover.context)
            .load("$YOUTUBE_THUMBNAIL_URL${item.key}/default.jpg")
            .apply(RequestOptions.placeholderOf(R.drawable.place_holder_movie_maniac))
            .into(itemView.itemVideoCover)
    }
    override fun onClick(v: View?) {
        onTapTrailer.onTapTrailer("$YOUTUBE_VIDEO_URL${trailerItem.key}")
    }
}