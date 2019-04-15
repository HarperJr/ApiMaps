package com.vlsu.maps.navigation.map

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class Navigator constructor(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int
) : Navigator {

    private var currentScreen = Navigation.DEFAULT_SCREEN

    override fun applyCommands(commands: Array<out Command>) {
        commands.forEach { command ->
            applyCommand(command)
        }
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> execForward(command.screen)
            is Replace -> execReplace(command.screen)
            is Back -> execBack()
        }
    }

    private fun execForward(screen: Screen) {
        val newScreen = Navigation.key(screen.screenKey)
        forward(fragment(newScreen))
        currentScreen = newScreen
    }

    private fun execReplace(screen: Screen) {
        val newScreen = Navigation.key(screen.screenKey)
        replace(fragment(newScreen))
        currentScreen = newScreen
    }

    private fun execBack() {
        val attachedFragment = fragment(currentScreen)
        fragmentManager.beginTransaction()
            .detach(attachedFragment)
            .commitNow()
    }

    private fun forward(newFragment: Fragment) {
        with(fragmentManager.beginTransaction()) {
            attach(newFragment)
            commitNow()
        }
    }

    private fun replace(toAttach: Fragment) {
        with(fragmentManager.beginTransaction()) {
            val toDetach = fragmentManager.findFragmentByTag(currentScreen.key)
            if (toDetach != null) {
                detach(toDetach)
            }
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