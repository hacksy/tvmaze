package com.hacksy.tvmaze.data.db

import androidx.room.*
import com.hacksy.tvmaze.data.db.utils.DateConverter
import com.hacksy.tvmaze.model.Image
import java.sql.Date

@Entity(tableName = "tb_episodes")
data class EpisodesDTO(
    @ColumnInfo(name = "tvShowId")  val tvShowId: Int,
    @ColumnInfo(name = "episodeId") @PrimaryKey val episodeId: Int,
    @ColumnInfo(name = "episodeName") val episodeName: String,
    @ColumnInfo(name = "season") val season: Int,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "image") val image: String?
)

