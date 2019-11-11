package com.calorificapp.di.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.calorificapp.CalorificApp
import com.calorificapp.di.component.DaggerAppComponent
import dagger.android.support.AndroidSupportInjection
import di.Injectable
import timber.log.Timber

/**
 * Helper class to automatically inject fragments if they implement [Injectable].
 */
object AppInjector {

    fun init(githubApp: CalorificApp) {
        DaggerAppComponent.builder().application(githubApp)
            .build().inject(githubApp)

        githubApp
            .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {
                    // empty
                }

                override fun onActivityResumed(activity: Activity) {
                    // empty
                }

                override fun onActivityPaused(activity: Activity) {
                    // empty
                }

                override fun onActivityStopped(activity: Activity) {
                    // empty
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
                    // empty
                }

                override fun onActivityDestroyed(activity: Activity) {
                    // empty
                }
            })
    }

    private fun handleActivity(activity: Activity) {
//        if (activity is Injectable) {
//            AndroidInjection.inject(activity)
//        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(
                            fm: FragmentManager,
                            f: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            Timber.tag("KEK").i("onFragmentCreated")
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
                )
        }
    }
}

