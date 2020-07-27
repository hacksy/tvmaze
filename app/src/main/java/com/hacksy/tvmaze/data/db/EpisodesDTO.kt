package com.hacksy.tvmaze.data.db

import androidx.room.*
import com.hacksy.tvmaze.data.db.utils.DateConverter
import java.sql.Date

@Entity(tableName = "tb_episodes")
data class EpisodesDTO(
    @ColumnInfo(name = "episodeId") @PrimaryKey val episodeId: Int,
    @ColumnInfo(name = "episodeName") val episodeName: String,
    @ColumnInfo(name = "season") val season: Integer,
    @ColumnInfo(name = "number") val number: Integer,
    @TypeConverters(DateConverter::class)
    val airstamp: Date,
    @Embedded val address: TvShowsDTO?
)

