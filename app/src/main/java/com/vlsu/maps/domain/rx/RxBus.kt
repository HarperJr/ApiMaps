package com.vlsu.maps.domain.rx

import com.vlsu.maps.di.scope.AppScope
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@AppScope
class RxBus @Inject constructor() {

    private val eventSubject = BehaviorSubject.create<Event>()

    fun onEvent(event: Event) = eventSubject.onNext(event)

    fun event(): Observable<Event> = eventSubject
}

sealed class Event
