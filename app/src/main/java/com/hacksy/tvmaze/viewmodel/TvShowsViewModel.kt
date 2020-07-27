package com.hacksy.tvmaze.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacksy.tvmaze.data.OperationResult
import com.hacksy.tvmaze.data.remote.TvShowsRemoteDataSource
import com.hacksy.tvmaze.model.TvShows
import com.hacksy.tvmaze.model.TvShowsDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowsViewModel(private val remoteRepository: TvShowsRemoteDataSource,
                      private val dbRepository: TvShowsDbRepository
): ViewModel() {

    private val _isViewLoading= MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    val tvShows = dbRepository.getTvShows()

    fun retrieveTvShows(){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            var  result: OperationResult<TvShows> = withContext(Dispatchers.IO){
                remoteRepository.retrieveTvShows()
            }
            _isViewLoading.postValue(false)
            Log.w("TAGTAG",result.toString());
            if(result is OperationResult.Success){
                withContext((Dispatchers.IO)) {
                    result.data?.let {
                        if (it.isNotEmpty()) dbRepository.sync(it)
                    }
                }
            }
        }
    }
}