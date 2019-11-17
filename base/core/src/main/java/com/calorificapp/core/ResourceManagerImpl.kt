package com.calorificapp.core

import android.content.Context
import com.calorificapp.pure_domain.ResourceManager
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(private val context: Context) : ResourceManager {

    override fun getString(id: Int): String {
        return context.getString(id)
    }
}