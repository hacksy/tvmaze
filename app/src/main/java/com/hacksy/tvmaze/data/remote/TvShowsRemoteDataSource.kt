package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.data.OperationResult
import com.hacksy.tvmaze.data.SingleOperationResult
import com.hacksy.tvmaze.model.TvEpisodes
import com.hacksy.tvmaze.model.TvShows


interface TvShowsRemoteDataSource {
    suspend fun listTvShows(page:Int): OperationResult<TvShows>
    suspend fun searchTvShows( show : String): OperationResult<TvShows>
    suspend fun getEpisodesByTvShow(tvShowId: String): OperationResult<TvEpisodes>

}