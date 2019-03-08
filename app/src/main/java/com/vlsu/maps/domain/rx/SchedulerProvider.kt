package com.vlsu.maps.domain.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun bg(): Scheduler

    fun ui(): Scheduler

    fun comp(): Scheduler

    fun db(): Scheduler
}