package com.calorificapp

import android.app.Activity
import android.app.Application
import com.calorificapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.paperdb.Paper
import timber.log.Timber
import javax.inject.Inject


class CalorificApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector as AndroidInjector<Any>
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Paper.init(this)
        initLogger()
        AppInjector.init(this)

    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: CalorificApp
    }

}