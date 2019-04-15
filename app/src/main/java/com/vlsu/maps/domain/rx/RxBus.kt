package com.vlsu.maps.domain.rx

import com.vlsu.maps.di.scope.AppScope
import com.vlsu.maps.domain.model.NotificationType
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@AppScope
class RxBus @Inject constructor() {

    private val eventSubject = PublishSubject.create<Event>()

    fun onEvent(event: Event) = eventSubject.onNext(event)

    fun event(): Observable<Event> = eventSubject
}

sealed class Event
data class EventNotification(val notification: String, val type: NotificationType)