package com.hacksy.tvmaze.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TvSeriesDTO::class], version = 1)
abstract class TvSeriesDatabase: RoomDatabase() {

    abstract fun tvSeriesDao(): TvSeriesDao
    abstract fun scheduleDao(): ScheduleDTO
    abstract fun episodesDao(): EpisodesDTO

    companion object {
        private var INSTANCE: TvSeriesDatabase? = null
        private const val DBNAME="tvSeriesDatabase.db"

        fun getInstance(context: Context): TvSeriesDatabase? {
            if (INSTANCE == null) {
                synchronized(TvSeriesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                        TvSeriesDatabase::class.java, DBNAME)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}