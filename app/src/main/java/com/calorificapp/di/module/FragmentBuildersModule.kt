package com.calorificapp.di.module

import com.calorificapp.features.account.AccountFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): AccountFragment

}
