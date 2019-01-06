package com.mmgoogleexpert.ptut.pmovie_tmdb.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.BASE_IMG_URL
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.requestGlideListener
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_detail_body.*
import kotlinx.android.synthetic.main.layout_detail_header.*

class MovieDetailActivity:BaseActivity() {
    private lateinit var movieItem: MovieItem
    companion object {
        fun newIntent(context: Context,movieItem: MovieItem):Intent{
            val intent = Intent(context, MovieDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("DetailItem", movieItem)
            intent.putExtra("DetailBundle", bundle)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieItem = (intent.getBundleExtra("DetailBundle").getSerializable("DetailItem") as MovieItem?)!!
        setSupportActionBar(movieDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        movieDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setUpUiComponentDetail(movieItem)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpUiComponentDetail(item:MovieItem){
        supportActionBar?.title=item.originalTitle
        detailHeaderTitle.text=item.title
        detailHeaderReleaseDate.text="Release Date : ${item.releaseDate}"
        detailHeaderStar.rating= (item.voteAverage!!.div(2)).toFloat()
        detailLanguage.text=item.originalLanguage
        detailOverview.text=item.overview
        detailVoteAverage.text="Vote Average : ${item.voteAverage}"
        item.backdropPath.let {
            Glide.with(this).load("$BASE_IMG_URL$it")
                .listener(requestGlideListener(movieImage))
                .into(movieImage)
        }


    }
}