package com.vlsu.maps.presentation.fragment

import android.support.design.widget.BottomSheetBehavior
import android.widget.FrameLayout

class FlexableBottomSheetBehavior : BottomSheetBehavior<FrameLayout>() {

    override fun isHideable(): Boolean {
        return true
    }
}