package com.calorificapp.data

import com.calorificapp.domain.repo.LocalStorage
import io.paperdb.Paper
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber

class PaperLocalStorage : LocalStorage {

    override fun write(tableName: String, key: String, data: Any): Completable {
        return Completable.fromAction {
            try {
                Paper.book(tableName).write(key, data)
            } catch (e: Exception) {
                Timber.d(e)
                delete(tableName, key)
            }
        }
    }

    override fun <T : Any> read(tableName: String, key: String): Single<T> {
        return Single.fromCallable {
            try {
                Paper.book(tableName).read<T>(key)?.let {
                    it
                } ?: throw Exception("No data in storage with key $key for table $tableName")
            } catch (e: Exception) {
                Timber.d(e)
                delete(tableName, key)
                throw Exception("Error read data with key $key for table $tableName")
            }
        }
    }

    override fun delete(tableName: String, key: String): Completable {
        return Completable.fromAction {
            try {
                Paper.book(tableName).delete(key)
            } catch (e: Exception) {
                Timber.d(e)
            }
        }
    }

    override fun erase(tableName: String): Completable {
        return Completable.fromAction {
            try {
                Paper.book(tableName).destroy()
            } catch (e: Exception) {
                Timber.d(e)
            }
        }
    }
}