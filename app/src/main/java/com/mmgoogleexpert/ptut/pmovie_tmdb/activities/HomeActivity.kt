package com.mmgoogleexpert.ptut.pmovie_tmdb.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.PopupMenu
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.components.EmptyViewPod
import com.mmgoogleexpert.ptut.pmovie_tmdb.inject.Injection
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.MoviePresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.HomeScreenView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.*
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_home_movie.*
import kotlinx.android.synthetic.main.app_bar_content_movie.*
import kotlinx.android.synthetic.main.fragment_content_movie.*
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.mmgoogleexpert.ptut.pmovie_tmdb.adapters.MovieAdapter
import kotlinx.android.synthetic.main.view_movie_item.*

class HomeActivity :
    BaseActivity(),
    HomeScreenView,
    PopupMenu.OnMenuItemClickListener{

    private val moviePresenter by lazyAndroid {
        ViewModelProviders.of(this,
            Injection.provideViewModelFactoryPopular(MoviePresenter()))
            .get(MoviePresenter::class.java)
    }
    private val movieAdapter by lazyAndroid { MovieAdapter(moviePresenter) }
    private val emptyViewPod  by lazyAndroid{ popularEmptyLayout as EmptyViewPod }

    companion object {
        fun newIntent(context: Context, fragmentIndex:Int): Intent {
            val intent= Intent(context,HomeActivity::class.java)
            intent.putExtra("FragmentIndex",fragmentIndex)
            return  intent
        }
    }

    override fun onCreate(
        savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_movie)

        setUpUIComponent()
        moviePresenter.errorLD.observe(this, this)
        moviePresenter.popularMovieListLD?.observe(this, Observer<List<MovieItem>> {
            swipeRefreshLayout.isRefreshing = false
            movieAdapter.submitList(it)
        })

        bottomAppBar.setNavigationOnClickListener {
            showPopup(it)
        }

        searchFab.setOnClickListener{
           startActivity(SearchMovieActivity.newIntent(this))
            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
        }
    }

    private fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        val inflater = popup.menuInflater
        popup.setOnMenuItemClickListener(this)
        inflater.inflate(R.menu.filter_menu, popup.menu)
        popup.show()
    }

    private fun setUpUIComponent() {
        toolbar_title.text= POPULAR_MOVIE
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.refreshingScheme(this)
        moviePresenter.initPresenter(this)
        moviePresenter.onNotifyCallPopularMovieList()

        itemRecycler.setUpGrid(this,emptyViewPod)
        itemRecycler.adapter = movieAdapter

        swipeRefreshLayout.setOnRefreshListener {
            moviePresenter.onNotifyCallPopularMovieList()
        }
    }

    override fun onChanged(error: Error?) {
        error?.let {
            swipeRefreshLayout.isRefreshing = false
            when (it) {
                is EmptyError -> {
                    emptyViewPod.setEmptyData(
                        R.drawable.empty_img,
                        "Product Not Found")
                }
                is NetworkError ->{
                    emptyViewPod.setEmptyData(
                        R.drawable.nointernet,
                        "No Internet Connection")
                }
            }
        }
    }


    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.popular -> {
                toolbar_title.text= POPULAR_MOVIE
                itemRecycler.adapter=null
                moviePresenter.onNotifyCallPopularMovieList()
                itemRecycler.adapter = movieAdapter
                movieAdapter.notifyDataSetChanged()
                true
            }
            R.id.topRated -> {
                toolbar_title.text= TOPRATED_MOVIE
                itemRecycler.adapter=null
                moviePresenter.onNotifyCallTopRatedMovieList()
                itemRecycler.adapter = movieAdapter
                movieAdapter.notifyDataSetChanged()
                true
            }
            R.id.upComing -> {
                toolbar_title.text= UPCOMING_MOVIE
                itemRecycler.adapter=null
                moviePresenter.onNotifyCallUpComingMovieList()
                itemRecycler.adapter = movieAdapter
                movieAdapter.notifyDataSetChanged()
                true
            }
            else -> false
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onLunchMovieDetail(movieItem: MovieItem, imageView:ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.transitionName)
        startActivity(MovieDetailActivity.newIntent(applicationContext,movieItem),options.toBundle())
    }
}
