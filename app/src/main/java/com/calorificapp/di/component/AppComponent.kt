package com.calorificapp.di.component

import android.app.Application
import com.calorificapp.CalorificApp
import com.calorificapp.di.module.AppModule
import com.calorificapp.di.module.FragmentBuildersModule
import com.calorificapp.di.module.MainActivityModule
import com.calorificapp.di.module.ResModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        FragmentBuildersModule::class,
        ResModule::class]
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