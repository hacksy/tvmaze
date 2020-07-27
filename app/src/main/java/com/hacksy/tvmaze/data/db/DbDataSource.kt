package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData

interface DbDataSource {
    fun tvSeries(): LiveData<List<TvSeriesDTO>>
    suspend fun addTvSeries(museums: List<TvSeriesDTO>)
    suspend fun deleteTVSeries()
}