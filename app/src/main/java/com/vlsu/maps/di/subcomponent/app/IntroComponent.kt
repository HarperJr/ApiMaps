package com.vlsu.maps.di.subcomponent.app

import com.vlsu.maps.presentation.fragment.intro.IntroFragment
import dagger.Subcomponent

@Subcomponent
interface IntroComponent {
    fun inject(introFragment: IntroFragment)
}