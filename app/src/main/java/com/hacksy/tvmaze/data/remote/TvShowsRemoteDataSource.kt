package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.data.OperationResult
import com.hacksy.tvmaze.model.TvShows


interface TvShowsRemoteDataSource {
    suspend fun retrieveTvShows(): OperationResult<TvShows>
}