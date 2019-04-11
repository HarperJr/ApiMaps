package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.notifying.NotifyingFragment
import com.vlsu.maps.presentation.fragment.notifying.NotifyingPresenter
import dagger.Subcomponent

@Subcomponent
interface NotifyingComponent {
    fun inject(notifyingFragment: NotifyingFragment)

    fun notifyingPresenter(): NotifyingPresenter
}