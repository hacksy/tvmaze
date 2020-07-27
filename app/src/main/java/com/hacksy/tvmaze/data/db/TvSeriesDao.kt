package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface TvSeriesDao {
    @Query("SELECT * from tb_tv_series")
    fun tvSeries(): LiveData<List<TvSeriesDTO>>

    @Insert(onConflict = REPLACE)
    suspend fun addTvSeries(museum: List<TvSeriesDTO>)

    @Query("DELETE from tb_tv_series")
    suspend fun deleteAllTvSeries()
}