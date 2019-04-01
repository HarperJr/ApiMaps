package com.vlsu.maps.navigation

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.vlsu.maps.presentation.OnBackPressable
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class MainNavigator constructor(
    activity: FragmentActivity,
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    private var currentScreen = Navigation.DEFAULT_SCREEN
    private val backPressableStack = mutableListOf<OnBackPressable>()

    override fun applyCommand(command: Command?) {
        when (command) {
            is Replace -> execReplace(command.screen)
            is Forward -> execForward(command.screen)
            is Back -> execBack()
            else -> super.applyCommand(command)
        }
    }

    private fun execReplace(screen: Screen) {
        val newScreen = Navigation.key(screen.screenKey)
        replace(fragment(currentScreen), fragment(newScreen))
        currentScreen = newScreen
    }

    private fun execForward(screen: Screen) {
        val newScreen = Navigation.key(screen.screenKey)
        forward(fragment(newScreen))
        currentScreen = newScreen
    }

    private fun execBack() {
        super.applyCommand(Back())
    }

    private fun replace(toDetach: Fragment?, toAttach: Fragment) {
        with(fragmentManager.beginTransaction()) {
            if (toDetach != null) {
                detach(toDetach)
            }
            attach(toAttach)
            commitNow()
        }
    }

    private fun forward(toAttach: Fragment) {
        with(fragmentManager.beginTransaction()) {
            attach(toAttach)
            commitNow()
        }
    }

    private fun fragment(screen: Navigation.Screen): Fragment {
        val fragment = fragmentManager.findFragmentByTag(screen.key)
        return fragment ?: with(fragmentManager) {
            val newFragment = Navigation.screen(screen).fragment
            with(beginTransaction()) {
                add(containerId, newFragment, screen.key)
                detach(newFragment)
                commitNow()
            }
            newFragment
        }
    }
}