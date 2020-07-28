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
    var isEmpty = MutableLiveData<Boolean>();
    val tvShows = dbRepository.getNonDeprecatedTvShows()

    fun listTvShows(page : Int = 1){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            var  result: OperationResult<TvShows> = withContext(Dispatchers.IO){
                remoteRepository.listTvShows(page)
            }
            _isViewLoading.postValue(false)
            if(result is OperationResult.Success){
                withContext((Dispatchers.IO)) {
                    result.data?.let {
                        if(it.isEmpty()){
                            isEmpty.postValue(true)
                        }else{
                            isEmpty.postValue(false)
                            dbRepository.append(it)
                        }
                    }
                }
            }
        }
    }

    fun checkForUpdates(s: String){
        android.util.Log.w("Debounced String", s);
        if(s.isNullOrEmpty()){
            listTvShows(1);
        }else{
            searchTvShows(s);
        }
    }
    fun searchTvShows(show : String){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            var  result: OperationResult<TvShows> = withContext(Dispatchers.IO){
                remoteRepository.searchTvShows(show)
            }
            _isViewLoading.postValue(false)
            if(result is OperationResult.Success){
                withContext((Dispatchers.IO)) {
                    result.data?.let {
                        if(it.isEmpty()){
                            Log.w("Updating Empty","Updated");
                            isEmpty.postValue(true)
                        }else{
                            isEmpty.postValue(false)
                            dbRepository.sync(it);
                        }
                    }
                }
            }
        }
    }
}