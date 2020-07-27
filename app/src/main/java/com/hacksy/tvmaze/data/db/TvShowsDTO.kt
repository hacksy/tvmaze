package com.hacksy.tvmaze.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Embedded

@Entity(tableName = "tb_tv_series")
data class TvShowsDTO(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "summary") val summary: String,
    @Embedded val schedule: ScheduleDTO?
)