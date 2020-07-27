package com.hacksy.tvmaze.data.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_schedule")
data class ScheduleDTO(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "time") val name: String,
    @ColumnInfo(name = "days") val days: String,
    @ColumnInfo(name = "rating") val rating: String,
    @ColumnInfo(name = "weight") val weight: String
)