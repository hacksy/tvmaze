package com.hacksy.tvmaze.data.db

import android.content.Context
import androidx.lifecycle.LiveData


class TvShowsDbDatasource(context:Context):DbDataSource {
    private lateinit var tvShowsDao:TvShowsDao

    init {
        val db = TvShowsDatabase.getInstance(context)
        db?.let {
            tvShowsDao = it.tvShowsDao()
        }
    }
    override fun tvShows(): LiveData<List<TvShowsDTO>> {
        return tvShowsDao.tvShows()
    }

    override suspend fun addTvShows(shows: List<TvShowsDTO>) {
        return tvShowsDao.addTvShows(shows)
    }

    override suspend fun deleteAllTvShows() {
        tvShowsDao.deleteAllTvShows()
    }
}