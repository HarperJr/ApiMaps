package com.vlsu.maps.data.database.transaction

import java.util.concurrent.Callable

interface DbTransaction {

    fun beginTransaction()

    fun endTransaction()

    fun runInTransaction(runnable: () -> Runnable)

    fun <T> runInTransaction(callable: () -> Callable<T>): T
}