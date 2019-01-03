package com.vlsu.maps.ui.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.vlsu.maps.R
import com.vlsu.maps.ui.MvpActivity
import com.vlsu.maps.ui.activity.mvp.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
