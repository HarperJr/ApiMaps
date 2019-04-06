package com.vlsu.maps.navigation

import ru.terrakok.cicerone.BaseRouter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import java.util.*

/**
 * Would be better if i migrate stack logic into navigator
 */
class FragmentRouter : BaseRouter() {

    private var navigator: Navigator? = null
    private var localStack = LinkedList<Screen>()

    fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    fun removeNavigator() {
        this.navigator = null
    }

    fun navigateTo(screen: Screen) {
        executeCommands(Forward(screen))
        localStack.add(screen)
    }

    fun replace(screen: Screen) {
        executeCommands(Replace(screen))
        if (localStack.size > 0) {
            localStack.removeLast()
        }
        localStack.add(screen)
    }

    fun back(): Boolean {
        return if (localStack.size > 0) {
            localStack.removeLast()
            executeCommands(Back())
            true
        } else {
            false
        }
    }

    override fun executeCommands(vararg commands: Command) {
        navigator?.applyCommands(commands)
    }
}