package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface TvShowsDao {
    @Query("SELECT * from tb_tv_shows")
    fun tvShows(): LiveData<List<TvShowsDTO>>


    @Query("SELECT * from tb_tv_shows WHERE id=:id LIMIT 1")
    fun findById(id :Int): LiveData<TvShowsDTO>

    @Query("SELECT * from tb_tv_shows WHERE current_query=1")
    fun newTvShows(): LiveData<List<TvShowsDTO>>

    @Insert(onConflict = REPLACE)
    suspend fun addTvShows(tvShows: List<TvShowsDTO>)

    @Query("UPDATE  tb_tv_shows SET current_query=0")
    suspend fun deprecatePreviousResults()

    @Query("DELETE from tb_tv_shows")
    suspend fun deleteAllTvShows()
}