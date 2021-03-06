package com.vlsu.maps.database.transaction

import com.vlsu.maps.database.Database
import java.util.concurrent.Callable

class DbTransactionImpl(private val database: Database):
    DbTransaction {

    override fun beginTransaction() {
        database.beginTransaction()
    }

    override fun endTransaction() {
        database.endTransaction()
    }

    override fun runInTransaction(runnable: () -> Runnable) {
        database.runInTransaction(runnable)
    }

    override fun <T> runInTransaction(callable: () -> Callable<T>): T {
        return database.runInTransaction(callable.invoke())
    }
}