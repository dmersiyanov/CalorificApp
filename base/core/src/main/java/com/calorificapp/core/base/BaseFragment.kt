package com.calorificapp.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerFragment


abstract class BaseFragment : DaggerFragment() {

    private var baseActivity: AppCompatActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }
}