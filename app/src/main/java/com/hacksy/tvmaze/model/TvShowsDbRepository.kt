package com.hacksy.tvmaze.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hacksy.tvmaze.data.db.DbDataSource
import com.hacksy.tvmaze.data.db.EpisodesDTO
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
                    itItem.weight,
                    itItem.favorite,
                    itItem.currentQuery
                )
            }
        }
    }
    suspend fun appendEpisodes(episodes: List<TvEpisodes>){
        dataSource.addEpisodes(episodes.map {
            //TODO: Constructor is too big, should Refactor
            val image = it.image?.medium ?:"";
            EpisodesDTO(
                it.tvShowId,
                it.id,
                it.name,
                (it.season).toInt(),
                it.number,
                it.summary,
                image
            )
        })
    }
    suspend fun sync(tvShowsList: List<TvShows>) {
        dataSource.deprecateAllShows()
        _addTvShows(tvShowsList)
    }
    private suspend fun _addTvShows(tvShowsList: List<TvShows>){
        dataSource.addTvShows(tvShowsList.map {
            //TODO: Constructor is too big, should Refactor
            val genres = it.genres?.joinToString { "," }?:"";
            val image = it.image?.medium ?:"";
            TvShowsDTO(
                it.id,
                it.name,
                genres,
                image,
                it.summary,
                it.schedule.time,
                it.schedule.days.joinToString { "," },
                it.rating.average,
                it.weight,
                it.favorite,
                true
            )
        })
    }
    suspend fun append(tvShowsList: List<TvShows>) {
        _addTvShows(tvShowsList)

    }
    fun getNonDeprecatedTvShows(): LiveData<List<TvShows>> {
        return Transformations.map(dataSource.newTvShows()) {
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
                    itItem.weight,
                    itItem.favorite,
                    itItem.currentQuery
                )
            }
        }
    }
}