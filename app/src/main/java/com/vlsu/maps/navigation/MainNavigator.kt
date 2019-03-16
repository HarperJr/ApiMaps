package com.vlsu.maps.navigation

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class MainNavigator constructor(
    activity: FragmentActivity,
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    private var currentScreen = Navigation.Screen.DEFAULT_SCREEN

    override fun applyCommand(command: Command?) {
        when (command) {
            is Replace -> execReplace(command.screen)
            is Forward -> execForward(command.screen)
            else -> super.applyCommand(command)
        }
    }

    private fun execReplace(screen: Screen) {
        val newScreen = Navigation.Screen.of(screen.screenKey)
        replace(fragment(currentScreen), fragment(newScreen))
        currentScreen = newScreen
    }

    private fun execForward(screen: Screen) {
        val newScreen = Navigation.Screen.of(screen.screenKey)
        forward(fragment(newScreen))
        currentScreen = newScreen
    }

    private fun replace(toDetach: Fragment?, toAttach: Fragment) {
        return with(fragmentManager.beginTransaction()) {
            if (toDetach != null) {
                detach(toDetach)
            }
            attach(toAttach)
            commitNow()
        }
    }

    private fun forward(toAttach: Fragment) {
        return with(fragmentManager.beginTransaction()) {
            attach(toAttach)
            commitNow()
        }
    }

    private fun fragment(screen: Navigation.Screen): Fragment {
        val fragment = fragmentManager.findFragmentByTag(screen.key)
        return fragment ?: with(fragmentManager) {
            val newFragment = Navigation.Screen.getScreen(screen).fragment
            with(beginTransaction()) {
                add(containerId, newFragment, screen.key)
                detach(newFragment)
                commitNow()
            }
            newFragment
        }
    }
}