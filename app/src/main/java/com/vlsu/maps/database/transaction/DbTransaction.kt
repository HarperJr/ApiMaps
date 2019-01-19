package com.vlsu.maps.database.transaction

import java.util.concurrent.Callable

interface DbTransaction {

    fun beginTransaction()

    fun endTransaction()

    fun runInTransaction(runnable: () -> Runnable)

    fun <T> runInTransaction(callable: () -> Callable<T>): T
}