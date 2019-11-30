package com.calorificapp.data.repo

import com.calorificapp.domain.repo.LocalStorage
import com.calorificapp.features.account.model.YearlyPics
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PicturesLocalRepoImpl(private val localStorage: LocalStorage) {

    fun save(yearlyPics: YearlyPics): Completable {
        localStorage.erase(PICS_TABLE)
        return localStorage.write(PICS_TABLE, YEARLY_KEY, yearlyPics)
            .subscribeOn(Schedulers.io())

    }

    fun get(): Single<YearlyPics> {
        return localStorage
            .read<YearlyPics>(PICS_TABLE, YEARLY_KEY)
            .subscribeOn(Schedulers.io())
    }

    companion object {

        const val PICS_TABLE = "pics_table"
        const val YEARLY_KEY = "yearly_key"
    }
}