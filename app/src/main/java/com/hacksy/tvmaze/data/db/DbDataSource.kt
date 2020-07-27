package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData

interface DbDataSource {
    fun tvSeries(): LiveData<List<TvSeriesDTO>>
    suspend fun addTvSeries(tvSeries: List<TvSeriesDTO>)
    suspend fun deleteAllTVSeries()
}