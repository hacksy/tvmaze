package com.hacksy.tvmaze.data.db

import android.content.Context
import androidx.lifecycle.LiveData


class TvShowsDbDatasource(context:Context):DbDataSource {
    private lateinit var tvShowsDao:TvShowsDao

    init {
        val db = TvShowsDatabase.getInstance(context)
        db?.let {
            tvShowsDao = it.tvSeriesDao()
        }
    }
    override fun tvSeries(): LiveData<List<TvShowsDTO>> {
        return tvShowsDao.tvSeries()
    }

    override suspend fun addTvSeries(shows: List<TvShowsDTO>) {
        return tvShowsDao.addTvSeries(shows)
    }

    override suspend fun deleteAllTVSeries() {
        tvShowsDao.deleteAllTvSeries()
    }
}