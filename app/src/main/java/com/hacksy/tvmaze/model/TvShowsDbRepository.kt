package com.hacksy.tvmaze.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hacksy.tvmaze.data.db.DbDataSource
import com.hacksy.tvmaze.data.db.TvShowsDTO

class TvShowsDbRepository(private val dataSource: DbDataSource) {

    fun getTvShows(): LiveData<List<TvSeries>> {
        return Transformations.map(dataSource.tvSeries()) {
            it.map { itItem ->
                //TODO: Constructor is too big, should Refactor
                TvSeries(
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

    suspend fun sync(tvSeriesList: List<TvSeries>) {
        dataSource.deleteAllTVSeries()
        dataSource.addTvSeries(tvSeriesList.map {
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