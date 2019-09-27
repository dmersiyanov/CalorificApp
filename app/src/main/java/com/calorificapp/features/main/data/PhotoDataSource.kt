package com.calorificapp.features.main.data

import com.calorificapp.features.main.model.MonthlyPics

interface PhotoDataSource {

    fun savePhoto(path: String)

    fun getPhotos(): MonthlyPics

}