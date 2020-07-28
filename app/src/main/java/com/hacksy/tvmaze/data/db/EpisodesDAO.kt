package com.hacksy.tvmaze.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EpisodesDAO {
    @Query("SELECT * from tb_episodes")
    fun tvShows(): LiveData<List<EpisodesDTO>>

    @Query("SELECT * from tb_episodes WHERE tvShowId=:id LIMIT 1")
    fun findById(id :Int) : LiveData<EpisodesDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTvShows(tvShows: List<EpisodesDTO>)

    @Query("UPDATE  tb_tv_shows SET current_query=0")
    suspend fun deprecatePreviousResults()

    @Query("DELETE from tb_tv_shows")
    suspend fun deleteAllTvShows()
}