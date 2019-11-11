package com.calorificapp.data.repo

import com.calorificapp.domain.repo.LocalStorage
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PicturesLocalRepoImpl(private val localStorage: LocalStorage) {

    fun save(yearlyPics: com.calorific.main.model.YearlyPics): Completable {
        localStorage.erase(PICS_TABLE)
        return localStorage.write(PICS_TABLE, YEARLY_KEY, yearlyPics)
            .subscribeOn(Schedulers.io())

    }

    fun get(): Single<com.calorific.main.model.YearlyPics> {
        return localStorage
            .read<com.calorific.main.model.YearlyPics>(PICS_TABLE, YEARLY_KEY)
            .subscribeOn(Schedulers.io())
    }

    companion object {

        const val PICS_TABLE = "pics_table"
        const val YEARLY_KEY = "yearly_key"
    }
}