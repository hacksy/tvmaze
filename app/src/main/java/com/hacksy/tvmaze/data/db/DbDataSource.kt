package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData

interface DbDataSource {
    fun tvSeries(): LiveData<List<TvShowsDTO>>
    suspend fun addTvSeries(tvSeries: List<TvShowsDTO>)
    suspend fun deleteAllTVSeries()
}