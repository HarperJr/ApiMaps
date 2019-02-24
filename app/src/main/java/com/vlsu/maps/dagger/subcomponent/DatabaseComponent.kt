package com.vlsu.maps.dagger.subcomponent

import com.vlsu.maps.dagger.module.DatabaseModule
import com.vlsu.maps.database.transaction.DbTransaction
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {

    fun dbTransaction(): DbTransaction

}