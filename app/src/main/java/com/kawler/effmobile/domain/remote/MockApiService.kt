package com.kawler.effmobile.domain.remote

import com.kawler.effmobile.domain.models.OfferResponse
import com.kawler.effmobile.domain.models.OffersTicketsResponse
import com.kawler.effmobile.domain.models.TicketsResponse
import retrofit2.http.GET

interface MockApiService {
    @GET("offers.json")
    suspend fun getFirstResponse(): OfferResponse

    @GET("offers_tickets.json")
    suspend fun getSecondResponse(): OffersTicketsResponse

    @GET("tickets.json")
    suspend fun getThirdResponse(): TicketsResponse
}