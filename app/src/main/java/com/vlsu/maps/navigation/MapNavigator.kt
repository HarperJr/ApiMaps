package com.vlsu.maps.navigation

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class MapNavigator constructor(
    private val fragment: Fragment,
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int
) : Navigator {

    private var currentScreen = MapNavigation.DEFAULT_SCREEN

    override fun applyCommands(commands: Array<out Command>) {
        commands.forEach { command ->
            applyCommand(command)
        }
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> applyForward(command.screen)
            is Replace -> applyReplace(command.screen)
            is Back -> applyBack()
        }
    }

    private fun applyForward(screen: Screen) {
        val newScreen = MapNavigation.key(screen.screenKey)
        forward(fragment(newScreen))
        currentScreen = newScreen
    }

    private fun applyReplace(screen: Screen) {
        val newScreen = MapNavigation.key(screen.screenKey)
        replace(fragment(currentScreen), fragment(newScreen))
        currentScreen = newScreen
    }

    private fun applyBack() {
        val currentFragment = fragment(currentScreen)
        with(fragmentManager.beginTransaction()) {
            detach(currentFragment)
            commitNow()
        }
    }

    private fun forward(newFragment: Fragment) {
        with(fragmentManager.beginTransaction()) {
            attach(newFragment)
            commitNow()
        }
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

    private fun fragment(screen: MapNavigation.Screen): Fragment {
        val fragment = fragmentManager.findFragmentByTag(screen.key)
        return fragment ?: with(fragmentManager) {
            val newFragment = MapNavigation.screen(screen).fragment
            with(beginTransaction()) {
                add(containerId, newFragment, screen.key)
                detach(newFragment)
                commitNow()
            }
            newFragment
        }
    }
}