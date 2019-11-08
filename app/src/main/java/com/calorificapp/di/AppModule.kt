package com.calorificapp.di

import dagger.Module


@Module(includes = [ViewModelModule::class])
public class AppModule {

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