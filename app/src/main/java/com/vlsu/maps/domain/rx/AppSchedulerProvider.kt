package com.vlsu.maps.domain.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AppSchedulerProvider : SchedulerProvider {
    override fun bg(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun comp(): Scheduler = Schedulers.computation()

    override fun db(): Scheduler = Schedulers.io()
}