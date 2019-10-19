package com.calorificapp.data.repo

import com.calorificapp.domain.repo.LocalStorage
import com.calorificapp.features.main.model.YearlyPics
import io.reactivex.Completable
import io.reactivex.Single

class PicturesLocalRepoImpl(private val localStorage: LocalStorage) {

    fun save(yearlyPics: YearlyPics): Completable {
        localStorage.erase(PICS_TABLE)
        return localStorage.write(PICS_TABLE, YEARLY_KEY, yearlyPics)
    }

    fun get(): Single<YearlyPics> {
        return localStorage.read(PICS_TABLE, YEARLY_KEY)
    }

    companion object {

        const val PICS_TABLE = "pics_table"
        const val YEARLY_KEY = "yearly_key"
    }
}