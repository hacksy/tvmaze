package com.hacksy.tvmaze.model
import androidx.room.ColumnInfo
import java.io.Serializable

data class TvSeries(val id:Int, val name:String, val  image:String, val  genres:String, val  summary:String):Serializable