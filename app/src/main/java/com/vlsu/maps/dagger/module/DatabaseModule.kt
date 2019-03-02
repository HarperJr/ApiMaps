package com.vlsu.maps.dagger.module

import android.arch.persistence.room.Room
import android.content.Context
import com.vlsu.maps.BuildConfig
import com.vlsu.maps.database.Database
import com.vlsu.maps.database.transaction.DbTransaction
import com.vlsu.maps.database.transaction.DbTransactionImpl
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

    @Provides
    fun provideDatabase(): Database {
        return Room.databaseBuilder(context, Database::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDbTransaction(database: Database): DbTransaction =
        DbTransactionImpl(database)
}