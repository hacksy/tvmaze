package com.hacksy.tvmaze.data.db

import androidx.room.*
import java.sql.Date

@Entity(tableName = "tb_episodes")
data class EpisodesDTO(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "season") val season: Integer,
    @ColumnInfo(name = "number") val number: Integer,
    @TypeConverters(DateConverter::class)
    val airstamp: Date,
    @Embedded val address: TvSeriesDTO?
)

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}