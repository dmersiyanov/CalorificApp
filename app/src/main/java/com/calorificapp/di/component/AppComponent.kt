package com.calorificapp.di.component

import android.app.Application
import com.calorific.main.di.AcoountFragmentModule
import com.calorificapp.CalorificApp
import com.calorificapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import di.MainActivityModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
//        MainActivityModule::class,
        AcoountFragmentModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(githubApp: CalorificApp)
}