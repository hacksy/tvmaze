package com.hacksy.tvmaze.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hacksy.tvmaze.R
import com.hacksy.tvmaze.di.Injection
import com.hacksy.tvmaze.model.TvShows
import com.hacksy.tvmaze.viewmodel.TvShowViewModel
import com.hacksy.tvmaze.viewmodel.TvShowsViewModel
import com.hacksy.tvmaze.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_home_tv_shows.*

class DetailTvShowsActivity : AppCompatActivity() {
    private lateinit var viewModel: TvShowViewModel

    companion object {
        const val TAG = "CONSOLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_shows)
        setupViewModel()
        setupUI()
        val tvShowId:String = intent.getStringExtra("tvShowId")

        viewModel.getEpisodesByTvShow(tvShowId);
    }

    private fun setupUI() {

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                Injection.providerRemoteRepository(),
                Injection.providerDBRepository()
            )
        ).get(TvShowViewModel::class.java)

        viewModel.tvShow.observe(this, renderTvShow)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)

    }

    //TODO: Use Timber instead of Log.v
    private val renderTvShow = Observer<TvShows> {
        Log.v(SearchTvShowsActivity.TAG, "data updated $it")
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        recyclerView.visibility= View.VISIBLE

    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(SearchTvShowsActivity.TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }
}