package com.calorificapp.di

import com.calorificapp.features.main.MainScreenViewModel
import dagger.Component

@Component
interface ViewModelComponent {

    fun getMainScreenViewModel(): MainScreenViewModel
}