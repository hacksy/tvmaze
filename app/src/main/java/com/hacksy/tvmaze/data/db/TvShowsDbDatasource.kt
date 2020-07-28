package com.hacksy.tvmaze.data.db

import android.content.Context
import androidx.lifecycle.LiveData


class TvShowsDbDatasource(context:Context):DbDataSource {
    private lateinit var tvShowsDao:TvShowsDao
    private lateinit var episodesDao:EpisodesDAO;
    init {
        val db = TvShowsDatabase.getInstance(context)
        db?.let {
            tvShowsDao = it.tvShowsDao()
            episodesDao = it.episodesDao()
        }
    }
    override fun tvShows(): LiveData<List<TvShowsDTO>> {
        return tvShowsDao.tvShows()
    }

    override suspend fun addTvShows(shows: List<TvShowsDTO>) {
        return tvShowsDao.addTvShows(shows)
    }

    override suspend fun addEpisodes(tvShows: List<EpisodesDTO>) {
        return episodesDao.addTvShows(tvShows)
    }

    override suspend fun deleteAllTvShows() {
        tvShowsDao.deleteAllTvShows()
    }

    override suspend fun deprecateAllShows() {
        tvShowsDao.deprecatePreviousResults()
    }

    override fun newTvShows(): LiveData<List<TvShowsDTO>> {
        return tvShowsDao.newTvShows()
    }
}