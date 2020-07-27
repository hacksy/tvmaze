package com.hacksy.tvmaze


import android.app.Application
import com.hacksy.tvmaze.di.Injection

class TvMazeApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Injection.setup(this)
    }
}