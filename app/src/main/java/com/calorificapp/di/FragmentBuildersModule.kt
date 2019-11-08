package com.calorificapp.di

import com.calorificapp.features.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

}
