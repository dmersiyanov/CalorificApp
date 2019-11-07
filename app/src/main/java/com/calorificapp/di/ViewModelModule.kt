package com.calorificapp.di

import androidx.lifecycle.ViewModel
import com.calorificapp.features.main.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    internal abstract fun demoViewModel(viewModel: MainScreenViewModel): ViewModel
}