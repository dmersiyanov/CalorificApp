package com.calorificapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module(includes = [ViewModelModule::class])
public class AppModule {


    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

//    private val BASE_URL = "https://api.github.com/"

//    @Singleton
//    @Provides
//    internal fun provideApp(): Retrofit {
//        return Retrofit.Builder().baseUrl(BASE_URL)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    internal fun provideRetrofitService(retrofit: Retrofit): RepoService {
//        return retrofit.create(RepoService::class.java)
//    }
}