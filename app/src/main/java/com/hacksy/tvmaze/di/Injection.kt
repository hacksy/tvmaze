package com.hacksy.tvmaze.di

import android.content.Context
import com.hacksy.tvmaze.data.db.DbDataSource
import com.hacksy.tvmaze.data.db.TvShowsDbDatasource
import com.hacksy.tvmaze.data.remote.TvShowsRemoteDataSource
import com.hacksy.tvmaze.model.TvShowsDbRepository
import com.hacksy.tvmaze.model.TvShowsRemoteRepository


object Injection {
    private val tvShowsRepository = TvShowsRemoteRepository()

    private lateinit var dbDataSource:DbDataSource
    private lateinit var tvShowsDbRepository: TvShowsDbRepository

    fun setup(context: Context){
        dbDataSource = TvShowsDbDatasource(context)
        tvShowsDbRepository = TvShowsDbRepository(dbDataSource)
    }

    fun providerDBRepository(): TvShowsDbRepository = tvShowsDbRepository
    fun providerRemoteRepository(): TvShowsRemoteDataSource = tvShowsRepository
}