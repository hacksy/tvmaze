package com.hacksy.tvmaze.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hacksy.tvmaze.data.remote.TvShowsRemoteDataSource
import com.hacksy.tvmaze.model.TvShowsDbRepository


class ViewModelFactory(private val remoteRepository: TvShowsRemoteDataSource,
                       private val dbRepository: TvShowsDbRepository
):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowsViewModel(remoteRepository,dbRepository) as T
    }
}