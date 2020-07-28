package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData
import com.hacksy.tvmaze.model.TvEpisodes

interface DbDataSource {
    fun tvShows(): LiveData<List<TvShowsDTO>>
    suspend fun addTvShows(tvShows: List<TvShowsDTO>)
    suspend fun addEpisodes(tvShows: List<EpisodesDTO>)
    suspend fun deleteAllTvShows()
    suspend fun deprecateAllShows()
    fun newTvShows(): LiveData<List<TvShowsDTO>>

}