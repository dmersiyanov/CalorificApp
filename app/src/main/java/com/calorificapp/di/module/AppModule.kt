package com.calorificapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }
}