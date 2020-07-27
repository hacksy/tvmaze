package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.data.OperationResult
import com.hacksy.tvmaze.model.TvSeries


interface TvSeriesRemoteDataSource {
    suspend fun retrieveTvSeries(): OperationResult<TvSeries>
}