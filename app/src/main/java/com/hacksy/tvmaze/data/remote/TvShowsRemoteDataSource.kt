package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.data.OperationResult
import com.hacksy.tvmaze.model.TvSeries


interface TvShowsRemoteDataSource {
    suspend fun retrieveTvSeries(): OperationResult<TvSeries>
}