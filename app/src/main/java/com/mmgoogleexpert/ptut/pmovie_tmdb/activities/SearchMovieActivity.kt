package com.mmgoogleexpert.ptut.pmovie_tmdb.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.adapters.SearchMovieAdapter
import com.mmgoogleexpert.ptut.pmovie_tmdb.components.EmptyViewPod
import com.mmgoogleexpert.ptut.pmovie_tmdb.inject.Injection
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.SearchMoviePresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.HomeScreenView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.lazyAndroid
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.setUpGrid
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*


class SearchMovieActivity:
        BaseActivity(),
    View.OnClickListener,
    HomeScreenView{


    companion object {
        fun newIntent(context: Context):Intent{
            return Intent(context,SearchMovieActivity::class.java)
        }
    }

    private val searchPresenter by lazyAndroid {
        ViewModelProviders.of(this,
            Injection.provideViewModelFactorySearch(SearchMoviePresenter()))
            .get(SearchMoviePresenter::class.java)
    }
    private val searchAdapter by lazyAndroid { SearchMovieAdapter(this,searchPresenter) }
    private val emptyViewPod  by lazyAndroid{ searchEmptyLayout as EmptyViewPod }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchPresenter.initPresenter(this)
        searchRecycler.setUpGrid(this,emptyViewPod)
        searchRecycler.adapter=searchAdapter
        initSearch()

        searchPresenter.errorLD.observe(this,this)
        searchPresenter.searchMovieListLD?.observe(this,Observer<List<MovieItem>>{
            searchSwipeRefresh.isRefreshing=false
            searchAdapter.setNewData(it as MutableList<MovieItem>)
        })

        ivBack.setOnClickListener(this)
    }

    private fun initSearch() {
        searchEdit.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                true
            } else {
                updateRepoListFromInput()
                false
            }
        }
        searchEdit.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
    }

    private fun updateRepoListFromInput() {
        val text = searchEdit.text.toString()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchEdit.windowToken, 0)

        searchSwipeRefresh.isRefreshing = true
        searchAdapter.clearData()
        searchPresenter.onNotifySearchMovie(text)
        searchSwipeRefresh.setOnRefreshListener {
            searchPresenter.onNotifySearchMovie(text)
        }
        ivBack.visibility= View.VISIBLE
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                startActivity(HomeActivity.newIntent(applicationContext,0))
                finish()
            }
            R.id.searchClear -> {
                searchEdit.text.clear()
            }
        }
    }

    override fun onChanged(error: Error?) {
        error?.let {
            searchSwipeRefresh.isRefreshing = false
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
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onLunchMovieDetail(movieItem: MovieItem, imageView: ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.transitionName)
        startActivity(MovieDetailActivity.newIntent(this,movieItem),options.toBundle())
    }
}