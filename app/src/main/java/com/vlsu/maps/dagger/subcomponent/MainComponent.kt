package com.vlsu.maps.dagger.subcomponent

import com.vlsu.maps.ui.activity.main.MainActivity
import com.vlsu.maps.ui.activity.main.mvp.MainPresenter
import dagger.Subcomponent

@Subcomponent
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    fun mainPresenter(): MainPresenter
}