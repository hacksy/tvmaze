package com.hacksy.tvmaze.di

import android.content.Context
import com.hacksy.tvmaze.data.db.DbDataSource
import com.hacksy.tvmaze.data.db.TvShowsDbDatasource
import com.hacksy.tvmaze.data.remote.TvShowsRemoteDataSource
import com.hacksy.tvmaze.model.TvShowsDbRepository
import com.hacksy.tvmaze.model.TvShowsRemoteRepository


object Injection {
    private val museumRepository = TvShowsRemoteRepository()

    private lateinit var dbDataSource:DbDataSource
    private lateinit var museumDbRepository: TvShowsDbRepository

    fun setup(context: Context){
        dbDataSource = TvShowsDbDatasource(context)
        museumDbRepository = TvShowsDbRepository(dbDataSource)
    }

    fun providerDBRepository(): TvShowsDbRepository = museumDbRepository
    fun providerRemoteRepository(): TvShowsRemoteDataSource = museumRepository
}