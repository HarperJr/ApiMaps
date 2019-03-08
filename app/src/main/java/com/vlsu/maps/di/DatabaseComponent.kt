package com.vlsu.maps.di

import com.vlsu.maps.data.database.transaction.DbTransaction
import com.vlsu.maps.di.module.DatabaseModule
import com.vlsu.maps.di.scope.DatabaseScope
import dagger.Component

@DatabaseScope
@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    fun dbTransaction(): DbTransaction
}