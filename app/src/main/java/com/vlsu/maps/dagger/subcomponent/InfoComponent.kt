package com.vlsu.maps.dagger.subcomponent

import com.vlsu.maps.ui.activity.main.fragment.control.mvp.InfoPresenter
import dagger.Subcomponent

@Subcomponent
interface InfoComponent {

    fun infoPresenter(): InfoPresenter
}