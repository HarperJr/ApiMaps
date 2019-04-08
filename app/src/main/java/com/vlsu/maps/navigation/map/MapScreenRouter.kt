package com.vlsu.maps.navigation.map

import ru.terrakok.cicerone.BaseRouter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

/**
 * Would be better if i migrate stack logic into navigator
 */
class MapScreenRouter(
    appRouter: Router
) : BaseRouter() {

    private var navigator: Navigator? = null

    fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    fun removeNavigator() {
        this.navigator = null
    }

    fun navigateTo(screen: Screen) {
        executeCommands(Forward(screen))
    }

    fun replace(screen: Screen) {
        executeCommands(Replace(screen))
    }

    fun back() {
        executeCommands(Back())
    }

    override fun executeCommands(vararg commands: Command) {
        navigator?.applyCommands(commands)
    }
}