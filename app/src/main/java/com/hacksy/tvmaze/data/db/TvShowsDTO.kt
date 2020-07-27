package com.hacksy.tvmaze.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Embedded

@Entity(tableName = "tb_tv_shows")
data class TvShowsDTO(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "summary") val summary: String,
    //Schedule fields
    @ColumnInfo(name = "scheduleTime") val scheduleTime: String,
    @ColumnInfo(name = "scheduleDays") val scheduleDays: String,
    @ColumnInfo(name = "scheduleRating") val scheduleRating: String,
    @ColumnInfo(name = "scheduleWeight") val scheduleWeight: String
)