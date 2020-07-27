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
                    Image(itItem.image),
                    itItem.genres.split(","),
                    itItem.summary,
                    Schedule(
                        itItem.scheduleTime,
                        itItem.scheduleDays.split(",")
                    ),
                    Rating(itItem.rating),
                    itItem.weight
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
                it.genres.joinToString { "," },
                it.image?.medium,
                it.summary,
                it.schedule.time,
                it.schedule.days.joinToString { "," },
                it.rating.average,
                it.weight
            )
        })
    }
}