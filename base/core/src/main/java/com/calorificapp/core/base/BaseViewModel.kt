package com.calorificapp.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.calorificapp.core.SubscriptionsHolder

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    SubscriptionsHolder {

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}