package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData

interface DbDataSource {
    fun tvShows(): LiveData<List<TvShowsDTO>>
    suspend fun addTvShows(tvShows: List<TvShowsDTO>)
    suspend fun deleteAllTvShows()
}