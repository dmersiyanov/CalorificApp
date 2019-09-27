package com.calorificapp.features.main

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.calorificapp.R
import com.calorificapp.features.learning.LearningFragment
import com.calorificapp.features.messages.MessagesFragment
import com.calorificapp.features.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findBottomFragment(BottomScreen.ACCOUNT)?.let {
            supportFragmentManager.commit {
                show(it)
            }
        } ?: run {
            supportFragmentManager.commit {
                add(
                    R.id.vFragmentContainer,
                    findBottomFragment(BottomScreen.ACCOUNT) ?: MainFragment.newInstance(),
                    BottomScreen.ACCOUNT.name
                )
            }
        }

        initBottomNavigation()
    }

    private fun showScreen(fragment: Fragment, screen: BottomScreen) {
        supportFragmentManager.commit {
            replace(
                R.id.vFragmentContainer,
                fragment,
                screen.name
            )
        }
    }

    private fun showAccountScreen() {
        showScreen(
            fragment = findBottomFragment(BottomScreen.ACCOUNT)
                ?: MainFragment.newInstance(),
            screen = BottomScreen.ACCOUNT
        )
    }

    private fun showLearningScreen() {
        showScreen(
            fragment = findBottomFragment(BottomScreen.LEARNING)
                ?: LearningFragment.newInstance(),
            screen = BottomScreen.LEARNING
        )
    }

    private fun showMessagesScreen() {
        showScreen(
            fragment = findBottomFragment(BottomScreen.MESSAGES)
                ?: MessagesFragment.newInstance(),
            screen = BottomScreen.MESSAGES
        )
    }

    private fun showSettingsScreen() {
        showScreen(
            fragment = findBottomFragment(BottomScreen.SETTINGS)
                ?: SettingsFragment.newInstance(),
            screen = BottomScreen.SETTINGS
        )
    }

    private fun findBottomFragment(screen: BottomScreen): Fragment? {
        return supportFragmentManager.findFragmentByTag(screen.name)
    }


    private fun initBottomNavigation() {
        vBottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_navigation_account -> {
                    showAccountScreen()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_navigation_learning -> {
                    showLearningScreen()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_navigation_messages -> {
                    showMessagesScreen()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_navigation_settings -> {
                    showSettingsScreen()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }

        }
    }

    private enum class BottomScreen {
        ACCOUNT,
        LEARNING,
        MESSAGES,
        SETTINGS,
    }
}
