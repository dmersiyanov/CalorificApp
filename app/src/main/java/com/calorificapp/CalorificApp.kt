package com.calorificapp

import android.app.Application
import io.paperdb.Paper

class CalorificApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }

}