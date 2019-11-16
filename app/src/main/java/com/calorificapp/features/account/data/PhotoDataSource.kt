package com.calorificapp.features.account.data

import com.calorificapp.features.account.model.MonthlyPics

interface PhotoDataSource {

    fun savePhoto(path: String)

    fun getPhotos(): MonthlyPics

}