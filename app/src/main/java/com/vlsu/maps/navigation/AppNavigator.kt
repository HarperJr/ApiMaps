package com.vlsu.maps.navigation

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import com.vlsu.maps.R
import com.vlsu.maps.presentation.fragment.offlinemap.OfflineMapSettingsFragment
import com.vlsu.maps.presentation.fragment.routing_compose.RoutingComposeFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class AppNavigator(
    activity: FragmentActivity,
    @IdRes containerId: Int
) : SupportAppNavigator(activity, containerId) {

    override fun setupFragmentTransaction(
        command: Command, currentFragment: Fragment?,
        nextFragment: Fragment, fragmentTransaction: FragmentTransaction
    ) {
        when (nextFragment) {
            is OfflineMapSettingsFragment,
            is RoutingComposeFragment -> fragmentTransaction
                .setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.enter_anim, R.anim.exit_anim)
        }
        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
    }
}