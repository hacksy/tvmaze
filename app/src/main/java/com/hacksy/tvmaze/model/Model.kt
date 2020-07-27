package com.hacksy.tvmaze.model

import androidx.room.ColumnInfo
import java.io.Serializable

data class TvShows(
    val id: Int,
    val name: String,
    val image: Image?,
    val genres: List<String>,
    val summary: String,
    val schedule: Schedule,
    val rating  : Rating,
    val weight: String
) : Serializable


data class Image(
    val medium: String?
) : Serializable

data class Rating(
    val average: Double
)

data class Schedule(
    val time: String,
    val days : List<String>
)
