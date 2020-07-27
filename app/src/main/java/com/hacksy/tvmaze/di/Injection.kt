package com.hacksy.tvmaze.di

import android.content.Context
import com.hacksy.tvmaze.data.db.DbDataSource
import com.hacksy.tvmaze.data.db.TvSeriesDbDatasource
import com.hacksy.tvmaze.data.remote.TvSeriesRemoteDataSource
import com.hacksy.tvmaze.model.TvSeriesDbRepository
import com.hacksy.tvmaze.model.TvSeriesRemoteRepository

object Injection {
    private val museumRepository = TvSeriesRemoteRepository()

    private lateinit var dbDataSource:DbDataSource
    private lateinit var museumDbRepository:TvSeriesDbRepository

    fun setup(context: Context){
        dbDataSource = TvSeriesDbDatasource(context)
        museumDbRepository = TvSeriesDbRepository(dbDataSource)
    }

    fun providerDBRepository(): TvSeriesDbRepository = museumDbRepository
    fun providerRemoteRepository(): TvSeriesRemoteDataSource = museumRepository
}