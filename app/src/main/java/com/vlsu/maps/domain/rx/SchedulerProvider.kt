package com.vlsu.maps.domain.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler

    fun ui(): Scheduler

    fun comp(): Scheduler
}