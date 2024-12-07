package com.kawler.effmobile.domain.repositories

import com.kawler.effmobile.domain.models.TicketsOffer
import kotlinx.coroutines.flow.Flow

interface TicketsOfferRepository {
    fun getAllTicketsOffers(): Flow<List<TicketsOffer>>
    suspend fun refreshTicketsOffers()
}