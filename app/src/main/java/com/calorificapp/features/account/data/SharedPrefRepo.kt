package com.calorificapp.features.account.data

import android.content.Context
import com.calorificapp.features.account.model.MonthlyPics

class SharedPrefRepo(context: Context): PhotoDataSource {

    private var reader = context.getSharedPreferences("pictures", Context.MODE_PRIVATE)
    private var editor = context.getSharedPreferences("pictures", Context.MODE_PRIVATE).edit()

    override fun savePhoto(path: String) {
        with(editor) {
            putString("img", path)
            commit()
        }

    }

    override fun getPhotos(): MonthlyPics {

        val picturesList = mutableListOf<String>()

        val photo = reader.getString("img", "")

        picturesList.add(photo ?: "")

        return MonthlyPics(picturesList)
    }
}