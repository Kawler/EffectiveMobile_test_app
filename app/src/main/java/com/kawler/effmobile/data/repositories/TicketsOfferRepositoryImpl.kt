package com.kawler.effmobile.data.repositories

import com.kawler.effmobile.data.db.TicketsOfferDao
import com.kawler.effmobile.domain.models.TicketsOffer
import com.kawler.effmobile.domain.remote.MockApiService
import com.kawler.effmobile.domain.repositories.TicketsOfferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketsOfferRepositoryImpl @Inject constructor(
    private val ticketsOfferDao: TicketsOfferDao,
    private val api: MockApiService
) : TicketsOfferRepository {

    override fun getAllTicketsOffers(): Flow<List<TicketsOffer>> =
        ticketsOfferDao.getAllTicketsOffers()

    override suspend fun refreshTicketsOffers() {
        try {
            val localCount = ticketsOfferDao.getAllTicketsOffersCount()
            val ticketsOffersFromApi = api.getSecondResponse().tickets_offers
            val apiCount = ticketsOffersFromApi.size
            if (localCount != apiCount) {
                ticketsOfferDao.deleteAllTicketsOffers()
                ticketsOfferDao.insertTicketsOffers(ticketsOffersFromApi)
            }
        } catch (_: Exception) {
        }
    }
}
