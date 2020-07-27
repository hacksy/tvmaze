package com.hacksy.tvmaze.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hacksy.tvmaze.data.db.DbDataSource
import com.hacksy.tvmaze.data.db.ScheduleDTO
import com.hacksy.tvmaze.data.db.TvShowsDTO

class TvShowsDbRepository(private val dataSource: DbDataSource) {

    fun getMuseums(): LiveData<List<TvSeries>> {
        return Transformations.map(dataSource.tvSeries()){
            it.map {itItem ->
                TvSeries(itItem.id,itItem.name, itItem.image, itItem.genres, itItem.summary)
            }
        }
    }

    suspend fun sync(tvSeriesList:List<TvSeries>){
        dataSource.deleteAllTVSeries()
        dataSource.addTvSeries(tvSeriesList.map {
            TvShowsDTO(it.id,it.name, it.image, it.genres, it.summary, ScheduleDTO(1,"","","",""))
        })
    }
}