package com.hacksy.tvmaze


import android.app.Application
import com.hacksy.tvmaze.di.Injection

class MuseumApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Injection.setup(this)
    }
}