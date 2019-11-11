package com.calorific.main.data

import com.calorific.main.model.MonthlyPics

interface PhotoDataSource {

    fun savePhoto(path: String)

    fun getPhotos(): MonthlyPics

}