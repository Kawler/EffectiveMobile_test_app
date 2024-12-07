package com.kawler.effmobile.data.di

import com.kawler.effmobile.data.db.OfferDao
import com.kawler.effmobile.data.db.PopularCityDao
import com.kawler.effmobile.data.db.TicketsDao
import com.kawler.effmobile.data.db.TicketsOfferDao
import com.kawler.effmobile.data.repositories.OfferRepositoryImpl
import com.kawler.effmobile.data.repositories.PopularCityRepositoryImpl
import com.kawler.effmobile.data.repositories.TicketsOfferRepositoryImpl
import com.kawler.effmobile.data.repositories.TicketsRepositoryImpl
import com.kawler.effmobile.domain.remote.MockApiService
import com.kawler.effmobile.domain.repositories.OfferRepository
import com.kawler.effmobile.domain.repositories.PopularCityRepository
import com.kawler.effmobile.domain.repositories.TicketsOfferRepository
import com.kawler.effmobile.domain.repositories.TicketsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideOfferRepository(
        offerDao: OfferDao,
        api: MockApiService
    ): OfferRepository {
        return OfferRepositoryImpl(offerDao, api)
    }

    @Provides
    @Singleton
    fun providePopularCityRepository(
        popularCityDao: PopularCityDao,
    ): PopularCityRepository {
        return PopularCityRepositoryImpl(popularCityDao)
    }

    @Provides
    @Singleton
    fun provideTicketsOfferRepository(
        ticketsOfferDao: TicketsOfferDao,
        api: MockApiService
    ): TicketsOfferRepository {
        return TicketsOfferRepositoryImpl(ticketsOfferDao, api)
    }

    @Provides
    @Singleton
    fun provideTicketsRepository(
        ticketDao: TicketsDao,
        api: MockApiService
    ): TicketsRepository {
        return TicketsRepositoryImpl(ticketDao, api)
    }
}