package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.info.mvp.InfoPresenter
import dagger.Subcomponent

@Subcomponent
interface InfoComponent {

    fun infoPresenter(): InfoPresenter
}