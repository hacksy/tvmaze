package com.hacksy.tvmaze.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hacksy.tvmaze.data.db.DbDataSource
import com.hacksy.tvmaze.data.db.TvShowsDTO

class TvShowsDbRepository(private val dataSource: DbDataSource) {

    fun getTvShows(): LiveData<List<TvShows>> {
        return Transformations.map(dataSource.tvShows()) {
            it.map { itItem ->
                //TODO: Constructor is too big, should Refactor
                TvShows(
                    itItem.id,
                    itItem.name,
                    itItem.image,
                    itItem.genres,
                    itItem.summary,
                    itItem.scheduleTime,
                    itItem.scheduleDays,
                    itItem.scheduleRating,
                    itItem.scheduleWeight
                )
            }
        }
    }

    suspend fun sync(tvShowsList: List<TvShows>) {
        dataSource.deleteAllTvShows()
        dataSource.addTvShows(tvShowsList.map {
            //TODO: Constructor is too big, should Refactor
            TvShowsDTO(
                it.id,
                it.name,
                it.image,
                it.genres,
                it.summary,
                it.scheduleTime,
                it.scheduleDays,
                it.scheduleRating,
                it.scheduleWeight
            )
        })
    }
}