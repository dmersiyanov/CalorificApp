package com.calorificapp.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface SubscriptionsHolder {

    /**
     * Holds Disposables
     */
    val disposables: CompositeDisposable

    /**
     * Easy activate disposables to composite with this function
     */
    fun Disposable.bind() = disposables.add(this)

    /**
     * Clear all subscriptions when needed
     */
    fun resetCompositeDisposable() {
        disposables.clear()
    }


}