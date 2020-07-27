package com.hacksy.tvmaze.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hacksy.tvmaze.R
import com.hacksy.tvmaze.di.Injection
import com.hacksy.tvmaze.model.TvShows
import com.hacksy.tvmaze.viewmodel.TvShowsViewModel
import com.hacksy.tvmaze.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_home_tv_shows.*
import kotlinx.android.synthetic.main.layout_error.*

class HomeTvShowsActivity : AppCompatActivity() {


    private lateinit var viewModel: TvShowsViewModel
    private lateinit var adapter: TvShowsAdapter
    companion object {
        const val TAG= "CONSOLE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_tv_shows)
        setupViewModel()
        setupUI()
    }

    private fun setupUI(){
        adapter= TvShowsAdapter(viewModel.tvShows.value?: emptyList())
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this, ViewModelFactory(
            Injection.providerRemoteRepository(),
            Injection.providerDBRepository())
        ).get(TvShowsViewModel::class.java)
        viewModel.tvShows.observe(this,renderTvShows)

        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
    }
    //TODO: Use Timber instead of Log.v
    private val renderTvShows= Observer<List<TvShows>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility= View.GONE
        layoutEmpty.visibility=View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver= Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility=if(it)View.VISIBLE else View.GONE
        progressBar.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility=View.VISIBLE
        layoutEmpty.visibility=View.GONE
        textViewError.text= "Error $it"
    }

    private val emptyListObserver= Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility=View.VISIBLE
        layoutError.visibility=View.GONE
    }
    //endregion observers

    //If you require updated data, you can call the method "loadMuseum" here
    override fun onResume() {
        super.onResume()
        viewModel.retrieveTvShows()
    }
}