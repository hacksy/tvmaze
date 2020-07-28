package com.hacksy.tvmaze.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hacksy.tvmaze.R
import com.hacksy.tvmaze.di.Injection
import com.hacksy.tvmaze.model.TvShows
import com.hacksy.tvmaze.viewmodel.TvShowsViewModel
import com.hacksy.tvmaze.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_home_tv_shows.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class SearchTvShowsActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main
    private var currentPage : Int = 1;

    private lateinit var viewModel: TvShowsViewModel
    private lateinit var adapter: TvShowsAdapter

    companion object {
        const val TAG = "CONSOLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_tv_shows)
        setupViewModel()
        setupUI()
        viewModel.listTvShows(currentPage)
    }

    private fun setupUI() {
        adapter = TvShowsAdapter(viewModel.tvShows.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                Injection.providerRemoteRepository(),
                Injection.providerDBRepository()
            )
        ).get(TvShowsViewModel::class.java)
        viewModel.tvShows.observe(this, renderTvShows)
        viewModel.isEmpty.observe(this,emptyListObserver);
        editText.addTextChangedListener(watcher)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        //To check if we are at bottom
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    currentPage += 1;
                    viewModel.listTvShows(currentPage)
                }
            }
        })
    }

    //TODO: Use Timber instead of Log.v
    private val renderTvShows = Observer<List<TvShows>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        recyclerView.visibility=View.VISIBLE

        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        textViewError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
        recyclerView.visibility=View.GONE
    }
    //endregion observers

    /*override fun onResume() {
        super.onResume()
        viewModel.listTvShows(1)
    }*/

    val watcher = object :TextWatcher{
        private var searchFor = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText == searchFor)
                return

            searchFor = searchText

            launch {
                delay(900)  //debounce timeOut
                if (searchText != searchFor)
                    return@launch

                if(s!=null && (s.length > 3 || s.isNullOrEmpty())){
                    viewModel.checkForUpdates(searchFor);
                }
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }
}


//Debouncer
