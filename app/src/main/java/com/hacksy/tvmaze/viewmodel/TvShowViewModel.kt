package com.hacksy.tvmaze.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacksy.tvmaze.data.OperationResult
import com.hacksy.tvmaze.data.remote.TvShowsRemoteDataSource
import com.hacksy.tvmaze.model.TvEpisodes
import com.hacksy.tvmaze.model.TvShows
import com.hacksy.tvmaze.model.TvShowsDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowViewModel(private val remoteRepository: TvShowsRemoteDataSource,
                       private val dbRepository: TvShowsDbRepository
): ViewModel() {

    private val _isViewLoading= MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading
    val tvShow = MutableLiveData<TvShows>()

    fun getEpisodesByTvShow(tvShowId : String){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            var  result: OperationResult<TvEpisodes> = withContext(Dispatchers.IO){
                remoteRepository.getEpisodesByTvShow(tvShowId)
            }
            _isViewLoading.postValue(false)
            if(result is OperationResult.Success){
                withContext((Dispatchers.IO)) {
                    result.data?.let {

                            dbRepository.appendEpisodes(it)

                    }
                }
            }
        }
    }


}