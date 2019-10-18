package com.calorificapp.domain.repo

import io.reactivex.Completable
import io.reactivex.Single

interface LocalStorage {
    fun write(tableName: String, key: String, data: Any): Completable
    fun <T : Any> read(tableName: String, key: String): Single<T>
    fun delete(tableName: String, key: String): Completable
    fun erase(tableName: String): Completable
}