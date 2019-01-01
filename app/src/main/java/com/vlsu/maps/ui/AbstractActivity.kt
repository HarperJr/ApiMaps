package com.vlsu.maps.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vlsu.maps.dagger.Dagger

abstract class AbstractActivity : AppCompatActivity() {

    protected val component = Dagger.getComponent()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}