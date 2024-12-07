package com.kawler.effmobile.data.di

import android.content.Context
import androidx.room.Room
import com.kawler.effmobile.data.db.EffMobileDatabase
import com.kawler.effmobile.data.db.OfferDao
import com.kawler.effmobile.data.db.PopularCityDao
import com.kawler.effmobile.data.db.TicketsDao
import com.kawler.effmobile.data.db.TicketsOfferDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EffMobileDatabase {
        return Room.databaseBuilder(
            context,
            EffMobileDatabase::class.java,
            "EffMobileDatabase"
        ).build()
    }

    @Provides
    fun providePopularCityDao(database: EffMobileDatabase): PopularCityDao {
        return database.popularCityDao()
    }

    @Provides
    fun provideOfferDao(database: EffMobileDatabase): OfferDao {
        return database.offerDao()
    }

    @Provides
    fun provideTicketsDao(database: EffMobileDatabase): TicketsDao {
        return database.ticketsDao()
    }

    @Provides
    fun provideTicketsOfferDao(database: EffMobileDatabase): TicketsOfferDao {
        return database.ticketsOfferDao()
    }
}