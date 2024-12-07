package com.kawler.effmobile.domain.repositories

import com.kawler.effmobile.domain.models.Offer
import kotlinx.coroutines.flow.Flow

interface OfferRepository {
    fun getAllOffers(): Flow<List<Offer>>
    suspend fun refreshOffers()
}