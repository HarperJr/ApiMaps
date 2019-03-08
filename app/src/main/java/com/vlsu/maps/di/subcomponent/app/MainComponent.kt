package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.activity.main.MainActivity
import com.vlsu.maps.presentation.activity.main.MainPresenter
import dagger.Subcomponent

@Subcomponent
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    fun mainPresenter(): MainPresenter
}