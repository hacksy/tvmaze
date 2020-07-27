package com.hacksy.tvmaze.data.db

import android.content.Context
import androidx.lifecycle.LiveData


class TvSeriesDbDatasource(context:Context):DbDataSource {
    private lateinit var tvSeriesDao:TvSeriesDao

    init {
        val db = TvSeriesDatabase.getInstance(context)
        db?.let {
            tvSeriesDao = it.tvSeriesDao()
        }
    }
    override fun tvSeries(): LiveData<List<TvSeriesDTO>> {
        return tvSeriesDao.tvSeries()
    }

    override suspend fun addTvSeries(series: List<TvSeriesDTO>) {
        return tvSeriesDao.addTvSeries(series)
    }

    override suspend fun deleteAllTVSeries() {
        tvSeriesDao.deleteAllTvSeries()
    }
}