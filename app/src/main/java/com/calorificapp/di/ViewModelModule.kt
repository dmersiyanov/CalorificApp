package com.calorificapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.calorificapp.features.main.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    internal abstract fun mainScreenViewModel(viewModel: MainScreenViewModel): ViewModel
}