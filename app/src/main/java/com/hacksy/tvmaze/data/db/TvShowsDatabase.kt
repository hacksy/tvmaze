package com.hacksy.tvmaze.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TvShowsDTO::class], version = 1)
abstract class TvShowsDatabase: RoomDatabase() {

    abstract fun tvSeriesDao(): TvShowsDao

    companion object {
        private var INSTANCE: TvShowsDatabase? = null
        private const val DBNAME="tvSeriesDatabase.db"

        fun getInstance(context: Context): TvShowsDatabase? {
            if (INSTANCE == null) {
                synchronized(TvShowsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                        TvShowsDatabase::class.java, DBNAME)
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