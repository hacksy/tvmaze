package com.hacksy.tvmaze.model

import androidx.room.ColumnInfo
import java.io.Serializable

data class TvShows(
    val id: Int,
    val name: String,
    val image: String,
    val genres: String,
    val summary: String,
    val scheduleTime: String,
    val scheduleDays: String,
    val scheduleRating: String,
    val scheduleWeight: String
) : Serializable

