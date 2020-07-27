package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface TvShowsDao {
    @Query("SELECT * from tb_tv_series")
    fun tvSeries(): LiveData<List<TvShowsDTO>>

    @Insert(onConflict = REPLACE)
    suspend fun addTvSeries(museum: List<TvShowsDTO>)

    @Query("DELETE from tb_tv_series")
    suspend fun deleteAllTvSeries()
}