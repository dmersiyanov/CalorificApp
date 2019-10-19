package com.calorificapp

import android.app.Application
import io.paperdb.Paper
import timber.log.Timber

class CalorificApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)

    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}