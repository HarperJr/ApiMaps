package com.vlsu.maps.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.data.database.Database
import com.vlsu.maps.data.database.transaction.DbTransaction
import com.vlsu.maps.data.database.transaction.DbTransactionImpl
import com.vlsu.maps.di.scope.DatabaseScope
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        RepositoryModule::class,
        MapperModule::class,
        DaoModule::class
    ]
)
class DatabaseModule(private val context: Context) {

    @DatabaseScope
    @Provides
    fun provideDatabase(): Database {
        return Room.databaseBuilder(context, Database::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @DatabaseScope
    @Provides
    fun provideDbTransaction(database: Database): DbTransaction =
        DbTransactionImpl(database)
}