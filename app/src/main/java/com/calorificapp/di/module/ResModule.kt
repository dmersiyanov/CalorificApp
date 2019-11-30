package com.calorificapp.di.module

import com.calorificapp.core.ResourceManagerImpl
import com.calorificapp.pure_domain.ResourceManager
import dagger.Binds
import dagger.Module

@Module
abstract class ResModule {

    @Binds
    abstract fun getResManager(resourceManagerImpl: ResourceManagerImpl): ResourceManager

}