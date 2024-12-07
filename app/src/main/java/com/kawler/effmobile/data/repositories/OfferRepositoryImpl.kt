package com.kawler.effmobile.data.repositories

import com.kawler.effmobile.data.db.OfferDao
import com.kawler.effmobile.domain.models.Offer
import com.kawler.effmobile.domain.remote.MockApiService
import com.kawler.effmobile.domain.repositories.OfferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfferRepositoryImpl @Inject constructor(
    private val offerDao: OfferDao,
    private val api: MockApiService
) : OfferRepository {
    override fun getAllOffers(): Flow<List<Offer>> = offerDao.getAllOffers()

    override suspend fun refreshOffers() {
        try {
            val localCount = offerDao.getAllOffersCount()
            val offersFromApi = api.getFirstResponse().offers
            val apiCount = offersFromApi.size

            if (localCount != apiCount) {
                offerDao.deleteAllOffers()
                offerDao.insertOffers(offersFromApi)
            }
        } catch (_: Exception) {
        }
    }
}